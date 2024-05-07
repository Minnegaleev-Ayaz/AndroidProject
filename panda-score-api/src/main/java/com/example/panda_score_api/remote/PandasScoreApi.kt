package com.example.panda_score_api.remote

import com.example.feature_predict_api.domain.model.UpcomingMatchesDataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface PandasScoreApi {
    @GET("csgo/matches/upcoming")
    suspend fun getUpcomingMatches(
        @Query("sort") sort:String ="",
        @Query("page") page:String = "1",
        @Query("per_pagge") perPage:String = "50"
    ): List<UpcomingMatchesDataModel>
}