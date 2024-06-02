package com.example.feature_schedule_api.domain.model


import com.google.gson.annotations.SerializedName

data class Videogame(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("slug")
    val slug: String?
)