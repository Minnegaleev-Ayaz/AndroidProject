package com.example.feature_predict_api.domain.repository

import com.example.feature_predict_api.domain.model.UpcomingMatchesPredictDataModel

interface PredictionRepository {
    suspend fun getUpcomingMatches(): List<UpcomingMatchesPredictDataModel>
}