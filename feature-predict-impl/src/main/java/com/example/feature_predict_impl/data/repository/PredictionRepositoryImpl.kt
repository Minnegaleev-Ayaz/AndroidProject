package com.example.feature_predict_impl.data.repository

import android.util.Log
import com.example.feature_predict_api.domain.model.UpcomingMatchesDataModel
import com.example.feature_predict_api.domain.repository.PredictionRepository

import com.example.panda_score_api.remote.PandasScoreApi
import javax.inject.Inject

class PredictionRepositoryImpl @Inject constructor(
    private val api: PandasScoreApi,
): PredictionRepository {
    override suspend fun getUpcomingMatches(): List<UpcomingMatchesDataModel> {
        return api.getUpcomingMatches()
    }
}