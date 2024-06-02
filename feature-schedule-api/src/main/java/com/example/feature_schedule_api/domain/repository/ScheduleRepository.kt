package com.example.feature_schedule_api.domain.repository

import com.example.feature_schedule_api.domain.model.MatchesDataModel

interface ScheduleRepository {
    suspend fun getMatches(matchType:String): List<MatchesDataModel>
}