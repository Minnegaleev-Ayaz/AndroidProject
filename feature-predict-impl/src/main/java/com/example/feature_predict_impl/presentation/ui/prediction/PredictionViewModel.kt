package com.example.feature_predict_impl.presentation.ui.prediction

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.feature_predict_impl.PredictionFeatureRouter
import com.example.feature_predict_impl.data.ExceptionHandlerDelegate
import com.example.feature_predict_impl.domain.model.UpcomingMatchesDomainModel
import com.example.feature_predict_impl.domain.usecases.UpcomingMatchesUseCase
import com.example.feature_predict_impl.presentation.model.MatchPresentationModel
import com.kpfu.itis.android_inception_23.data.runCatching
import com.nefrit.common.base.BaseViewModel
import com.nefrit.common.utils.AsyncResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PredictionViewModel(
    private val upcomingMatchesUseCase: UpcomingMatchesUseCase,
    private val router: PredictionFeatureRouter,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,

) : BaseViewModel() {
    private val _uncomingMatchesFlow =
        MutableStateFlow<AsyncResult<List<MatchPresentationModel>>?>(AsyncResult.Loading)
    val uncomingMatchesFlow: StateFlow<AsyncResult<List<MatchPresentationModel>>?>
        get() = _uncomingMatchesFlow

    fun initialize() {
        viewModelScope.launch {
            _uncomingMatchesFlow.emit(AsyncResult.Loading)
            runCatching(exceptionHandlerDelegate) {
                upcomingMatchesUseCase.invoke()
            }.onSuccess {
                Log.e("Ayaz", "succes")
                _uncomingMatchesFlow.emit(AsyncResult.Success(it))
            }.onFailure {
                it.message?.let { it1 -> Log.e("Ayaz", it1) }
                _uncomingMatchesFlow.emit(AsyncResult.Error(it))
            }
        }
    }
}