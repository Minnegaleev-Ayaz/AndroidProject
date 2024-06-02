package com.example.feature_predict_impl.presentation.ui.prediction.last_matches_statistic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.feature_predict_impl.PredictionFeatureRouter
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

class StatisticAssistedViewModel @AssistedInject constructor(
    private val router: PredictionFeatureRouter,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
    @Assisted(value = PARAM_KEY) private val param: Int,
) : BaseViewModel() {
    /*private val _pastMatchesFlow =
        MutableStateFlow<AsyncResult<List<MatchPresentationModel>>?>(AsyncResult.Loading)
    val pastMatchesFlow: StateFlow<AsyncResult<List<MatchPresentationModel>>?>
        get() = _pastMatchesFlow*/
    private val _pastMatchesFlow =
        MutableStateFlow<AsyncResult<Int>>(AsyncResult.Loading)
    val pastMatchesFlow: StateFlow<AsyncResult<Int>>
        get() = _pastMatchesFlow

    fun initialize() {
        viewModelScope.launch {
            _pastMatchesFlow.emit(AsyncResult.Loading)
            runCatching(exceptionHandlerDelegate) {
            }.onSuccess {
            }.onFailure {
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted(PARAM_KEY) param: Int): StatisticAssistedViewModel
    }

    companion object {
        const val PARAM_KEY = "ParamKey"

        @Suppress("UNCHECKED_CAST")
        fun provideFactory(
            assistedFactory: Factory,
            assistedParam: Int,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(assistedParam) as T
            }
        }
    }
}