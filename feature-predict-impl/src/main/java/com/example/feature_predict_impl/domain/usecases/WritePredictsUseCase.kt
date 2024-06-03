package com.example.feature_predict_impl.domain.usecases

import com.example.feature_predict_impl.domain.PredictFBRepository
import com.example.feature_predict_impl.domain.model.Predict
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WritePredictsUseCase @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val dispatcher: CoroutineDispatcher,
    private val predictFBRepository: PredictFBRepository,
) {
    suspend operator fun invoke(matchId:String,vote:Int) {
        return withContext(dispatcher) {
            predictFBRepository.createPredict(Predict(firebaseAuth.currentUser!!.uid, matchId,vote.toString()))
        }
    }
}