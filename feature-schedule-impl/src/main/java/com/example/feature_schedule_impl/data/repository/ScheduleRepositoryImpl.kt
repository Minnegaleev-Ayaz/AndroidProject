package com.example.feature_schedule_impl.data.repository

import android.util.Log
import com.example.feature_schedule_api.domain.model.MatchesDataModel
import com.example.feature_schedule_api.domain.repository.ScheduleRepository

import com.example.panda_score_api.remote.PandasScoreApi
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val api: PandasScoreApi,

): ScheduleRepository {

    override suspend fun getMatches(matchType: String): List<MatchesDataModel> {
        val res = api.getMatches(matchType)
        return res
    }
}