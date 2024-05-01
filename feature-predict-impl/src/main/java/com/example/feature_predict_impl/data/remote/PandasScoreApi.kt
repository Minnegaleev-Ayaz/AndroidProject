package com.example.feature_predict_impl.data.remote

import com.example.feature_predict_api.domain.model.UpcomingMatchesDataModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PandasScoreApi {
    @GET("csgo/matches/upcoming?token=jHpOJg5KP8bOwd0aU2pM6abcnGo6Us85zULTVKTJKAUfOvcM0cg")
    suspend fun getUpcomingMatches(): List<UpcomingMatchesDataModel>
}