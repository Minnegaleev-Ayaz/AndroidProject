package com.example.feature_predict_impl.presentation.ui.prediction.prediction

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.feature_schedule_impl.ScheduleFeatureRouter
import com.example.feature_schedule_impl.domain.mapper.FromDomainToPresentationMapper
import com.example.feature_schedule_impl.domain.usecases.MatchesUseCase
import com.example.feature_schedule_impl.presentation.model.past.PastMatchUiModel
import com.example.feature_schedule_impl.presentation.model.running.RunningMatchUiModel
import com.example.feature_schedule_impl.presentation.model.upcoming.UpcomingMatchUiModel
import com.example.feature_schedule_impl.presentation.view_pager.ViewPagerFragment
import com.example.panda_score_api.remote.ExceptionHandlerDelegate
import com.example.panda_score_api.remote.runCatching
import com.nefrit.common.base.BaseViewModel
import com.nefrit.common.utils.AsyncResult
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ScheduleAssistedViewModel @AssistedInject constructor(
    private val mapper: FromDomainToPresentationMapper,
    private val matchesUseCase: MatchesUseCase,
    private val router: ScheduleFeatureRouter,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
    @Assisted(value = PARAM_KEY) private val param: String
) : BaseViewModel() {
    private val _upcomingMatchesFlow =
        MutableStateFlow<AsyncResult<List<UpcomingMatchUiModel>>?>(AsyncResult.Loading)
    val upcomingMatchesFlow: StateFlow<AsyncResult<List<UpcomingMatchUiModel>>?>
        get() = _upcomingMatchesFlow
    private val _runningMatchesFlow =
        MutableStateFlow<AsyncResult<List<RunningMatchUiModel>>?>(AsyncResult.Loading)
    val runningMatchesFlow: StateFlow<AsyncResult<List<RunningMatchUiModel>>?>
        get() = _runningMatchesFlow
    private val _pastMatchesFlow =
        MutableStateFlow<AsyncResult<List<PastMatchUiModel>>?>(AsyncResult.Loading)
    val pastMatchesFlow: StateFlow<AsyncResult<List<PastMatchUiModel>>?>
        get() = _pastMatchesFlow

    fun initialize() {
        viewModelScope.launch {
            when (param) {
                ViewPagerFragment.past -> {
                    _pastMatchesFlow.emit(AsyncResult.Loading)
                    runCatching(exceptionHandlerDelegate) {
                        matchesUseCase.invoke(param).filter {
                          it.beginAt!=null
                        }
                            .map { it -> mapper.mapDomainToPresentationPast(it) }
                    }.onSuccess {
                        _pastMatchesFlow.emit(AsyncResult.Success(it))
                    }.onFailure {
                            _pastMatchesFlow.emit(AsyncResult.Error(it))
                    }
                }

                ViewPagerFragment.running -> {
                    _runningMatchesFlow.emit(AsyncResult.Loading)
                    runCatching(exceptionHandlerDelegate) {
                        matchesUseCase.invoke(param).filter {
                            it.beginAt!=null
                        }
                            .map { it -> mapper.mapDomainToPresentationRunning(it) }
                    }.onSuccess {
                        _runningMatchesFlow.emit(AsyncResult.Success(it))
                    }.onFailure {
                        _runningMatchesFlow.emit(AsyncResult.Error(it))
                    }
                }

                ViewPagerFragment.upcoming -> {
                    _upcomingMatchesFlow.emit(AsyncResult.Loading)
                    runCatching(exceptionHandlerDelegate) {
                        matchesUseCase.invoke(param).filter {
                            it.beginAt!=null
                        }
                            .map { it -> mapper.mapDomainToPresentationUpcoming(it) }
                    }.onSuccess {
                        _upcomingMatchesFlow.emit(AsyncResult.Success(it))
                    }.onFailure {
                        _upcomingMatchesFlow.emit(AsyncResult.Error(it))
                    }
                }
            }
        }
    }
    fun openStreamsBottomSheetDialog(model:RunningMatchUiModel){
        router.openStreamsFromRunning(model)
    }
    fun openUpcomingBottomSheetDialog(model:UpcomingMatchUiModel){
        router.openUpcomingBottom(model)
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted(PARAM_KEY) param: String): ScheduleAssistedViewModel
    }

    companion object {
        const val PARAM_KEY = "ParamKey"

        @Suppress("UNCHECKED_CAST")
        fun provideFactory(
            assistedFactory: Factory,
            assistedParam: String,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(assistedParam) as T
            }
        }
    }
}