package com.example.feature_predict_impl.presentation.ui.prediction.prediction

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.feature_predict_impl.PredictionFeatureRouter
import com.example.feature_predict_impl.domain.mapper.FromDomainToPresentationMapper
import com.example.feature_predict_impl.domain.usecases.UpcomingMatchesUseCase
import com.example.feature_predict_impl.presentation.model.MatchPresentationModel
import com.example.panda_score_api.remote.ExceptionHandlerDelegate
import com.example.panda_score_api.remote.runCatching
import com.nefrit.common.base.BaseViewModel
import com.nefrit.common.utils.AsyncResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PredictionViewModel @Inject constructor(
    private val upcomingMatchesUseCase: UpcomingMatchesUseCase,
    private val router: PredictionFeatureRouter,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
    private val mapper: FromDomainToPresentationMapper,
) : BaseViewModel() {
    private val _uncomingMatchesFlow =
        MutableStateFlow<AsyncResult<List<MatchPresentationModel>>?>(AsyncResult.Loading)
    val uncomingMatchesFlow: StateFlow<AsyncResult<List<MatchPresentationModel>>?>
        get() = _uncomingMatchesFlow

    fun initialize() {
        viewModelScope.launch {
            _uncomingMatchesFlow.emit(AsyncResult.Loading)
            runCatching(exceptionHandlerDelegate) {
                upcomingMatchesUseCase.invoke().filter {
                    it.beginAt!=null
                }.map { it-> mapper.mapDomainToPresentation(it) }
            }.onSuccess {
                _uncomingMatchesFlow.emit(AsyncResult.Success(it))
            }.onFailure {
                _uncomingMatchesFlow.emit(AsyncResult.Error(it))
            }
        }
    }
    fun openBottomPredict(model:MatchPresentationModel){
        router.openBottomFromMain(model)
    }
}