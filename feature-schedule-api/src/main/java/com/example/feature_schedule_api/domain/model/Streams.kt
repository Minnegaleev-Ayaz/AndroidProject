package com.example.feature_schedule_api.domain.model


import com.google.gson.annotations.SerializedName

data class Streams(
    @SerializedName("embed_url")
    val embedUrl: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("main")
    val main: Boolean,
    @SerializedName("official")
    val official: Boolean,
    @SerializedName("raw_url")
    val rawUrl: String
)