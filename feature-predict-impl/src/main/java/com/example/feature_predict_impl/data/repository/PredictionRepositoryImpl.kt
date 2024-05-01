package com.example.feature_predict_impl.data.repository

import android.util.Log
import com.example.feature_predict_api.domain.model.UpcomingMatchesDataModel
import com.example.feature_predict_api.domain.repository.PredictionRepository
import com.example.feature_predict_impl.data.mapper.FromDataToDomainMapper
import com.example.feature_predict_impl.data.remote.PandasScoreApi
import com.example.feature_predict_impl.domain.model.UpcomingMatchesDomainModel
import javax.inject.Inject

class PredictionRepositoryImpl @Inject constructor(
    private val api: PandasScoreApi,
): PredictionRepository {
    override suspend fun getUpcomingMatches(): List<UpcomingMatchesDataModel> {
        return api.getUpcomingMatches()
    }
}