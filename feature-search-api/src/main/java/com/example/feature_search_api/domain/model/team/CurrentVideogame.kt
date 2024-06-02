package com.example.feature_search_api.domain.model.team


import com.google.gson.annotations.SerializedName

data class CurrentVideogame(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("slug")
    val slug: String?
)