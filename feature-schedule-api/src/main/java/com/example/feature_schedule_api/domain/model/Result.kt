package com.example.feature_schedule_api.domain.model


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("score")
    val score: Int,
    @SerializedName("team_id")
    val teamId: Int
)