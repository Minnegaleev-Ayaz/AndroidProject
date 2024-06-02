package com.example.feature_schedule_api.domain.model


import com.google.gson.annotations.SerializedName

data class Winner(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("type")
    val type: String?
)