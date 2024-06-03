package com.example.feature_predict_impl.domain.usecases

import com.example.feature_predict_impl.domain.PredictFBRepository
import com.example.feature_predict_impl.presentation.model.PredictPresentationModel
import com.google.firebase.auth.FirebaseAuth
import com.nefrit.common.utils.AsyncResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ReadMyPredictUseCase @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val dispatcher: CoroutineDispatcher,
    private val predictFBRepository: PredictFBRepository,
) {
    suspend operator fun invoke(
        matchId:Long,
        stateFlow: MutableStateFlow<AsyncResult<PredictPresentationModel>?>
    ) {
        return withContext(dispatcher) {
            predictFBRepository.readMyPredicts(matchId,firebaseAuth.currentUser!!.uid,stateFlow)
        }
    }
}