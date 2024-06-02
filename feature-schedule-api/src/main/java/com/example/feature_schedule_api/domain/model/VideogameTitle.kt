package com.example.feature_schedule_api.domain.model


import com.google.gson.annotations.SerializedName

data class VideogameTitle(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("videogame_id")
    val videogameId: Int?
)