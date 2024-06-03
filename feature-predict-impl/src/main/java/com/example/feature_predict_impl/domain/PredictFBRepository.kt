package com.example.feature_predict_impl.domain

import com.example.feature_predict_impl.domain.model.Predict
import com.example.feature_predict_impl.presentation.model.PredictPresentationModel
import com.nefrit.common.utils.AsyncResult
import kotlinx.coroutines.flow.MutableStateFlow

interface PredictFBRepository {
    fun createPredict(predict: Predict)
    fun readPredicts(
        matchId:Long,
        stateFlow: MutableStateFlow<AsyncResult<List<PredictPresentationModel>>?>
    )
    fun readMyPredicts(
        matchId:Long,
        userId:String,
        stateFlow: MutableStateFlow<AsyncResult<PredictPresentationModel>?>
    )
    fun updatePredict(predict: Predict)
}