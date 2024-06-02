package com.example.panda_score_api.remote

import com.example.feature_predict_api.domain.model.UpcomingMatchesPredictDataModel
import com.example.feature_schedule_api.domain.model.MatchesDataModel
import com.example.feature_search_api.domain.model.player.PlayerDataModel
import com.example.feature_search_api.domain.model.team.TeamDataModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PandasScoreApi {
    @GET("csgo/matches/upcoming")
    suspend fun getUpcomingMatchesForPredict(
        @Query("sort") sort: String = "",
        @Query("page") page: String = "1",
        @Query("per_pagge") perPage: String = "50"
    ): List<UpcomingMatchesPredictDataModel>

    @GET("csgo/matches/{match_type}")
    suspend fun getMatches(
        @Path("match_type") matchType: String?,
        @Query("sort") sort: String = "",
        @Query("page") page: String = "1",
        @Query("per_pagge") perPage: String = "50"
    ): List<MatchesDataModel>
    @GET("csgo/players/")
    suspend fun  getPlayers(
        @Query("search[name]") name: String?,
        @Query("sort") sort: String = "",
        @Query("page") page: String = "1",
        @Query("per_pagge") perPage: String = "50"
    ):List<PlayerDataModel>
    @GET("csgo/teams/")
    suspend fun  getTeams(
        @Query("search[name]") name: String?,
        @Query("sort") sort: String = "",
        @Query("page") page: String = "1",
        @Query("per_pagge") perPage: String = "50"
    ):List<TeamDataModel>
}