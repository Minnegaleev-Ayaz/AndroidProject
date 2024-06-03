package com.example.feature_predict_impl.data.repository

import android.util.Log
import com.example.feature_predict_impl.domain.PredictFBRepository
import com.example.feature_predict_impl.domain.mapper.FromDomainToPresentationMapper
import com.example.feature_predict_impl.domain.model.Predict
import com.example.feature_predict_impl.presentation.model.PredictPresentationModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.nefrit.common.core.preferences.Preferences
import com.nefrit.common.utils.AsyncResult
import kotlinx.coroutines.flow.MutableStateFlow

import javax.inject.Inject

class PredictFBRepositoryImpl @Inject constructor(
    private val mapper: FromDomainToPresentationMapper,
    private val firebaseDataBase: DatabaseReference
) : PredictFBRepository {
    override fun createPredict(predict: Predict) {
        val hashMap: HashMap<String, String> = HashMap()

        hashMap["userId"] = predict.userId ?: ""
        hashMap["matchId"] = predict.matchId.toString()
        hashMap["vote"] = predict.vote.toString()

        firebaseDataBase.push().setValue(hashMap)
    }

    override fun readPredicts(
        matchId: Long,
        stateFlow: MutableStateFlow<AsyncResult<List<PredictPresentationModel>>?>
    ) {
        val predictList = ArrayList<Predict>()
        firebaseDataBase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapShot: DataSnapshot in snapshot.children) {
                    val predict = dataSnapShot.getValue(Predict::class.java)
                    if (predict?.matchId == (matchId.toString())) {
                        predictList.add(predict)
                    }
                }
                stateFlow.value = AsyncResult.Success(predictList.map { mapper.mapPredict(it) })
            }

            override fun onCancelled(error: DatabaseError) {
                stateFlow.value = AsyncResult.Error(error.toException())
            }
        })
    }

    override fun readMyPredicts(
        matchId: Long,
        userId: String,
        stateFlow: MutableStateFlow<AsyncResult<PredictPresentationModel>?>
    ) {
        firebaseDataBase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapShot: DataSnapshot in snapshot.children) {
                    val predict = dataSnapShot.getValue(Predict::class.java)
                    if (predict?.matchId == (matchId.toString()) && (userId == predict?.userId)) {
                        stateFlow.value = AsyncResult.Success(mapper.mapPredict(predict))
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                stateFlow.value = AsyncResult.Error(error.toException())
            }
        })
    }

    override fun updatePredict(predict: Predict) {
    }

}