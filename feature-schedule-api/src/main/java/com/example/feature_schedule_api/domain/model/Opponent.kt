package com.example.feature_schedule_api.domain.model


import com.google.gson.annotations.SerializedName

data class Opponent(
    @SerializedName("opponent")
    val opponent: OpponentX,
    @SerializedName("type")
    val type: String?
)