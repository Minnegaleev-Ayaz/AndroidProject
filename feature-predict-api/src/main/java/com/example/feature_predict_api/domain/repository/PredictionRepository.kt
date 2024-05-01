package com.example.feature_predict_api.domain.repository

import com.example.feature_predict_api.domain.model.UpcomingMatchesDataModel

interface PredictionRepository {
    suspend fun getUpcomingMatches(): List<UpcomingMatchesDataModel>
}