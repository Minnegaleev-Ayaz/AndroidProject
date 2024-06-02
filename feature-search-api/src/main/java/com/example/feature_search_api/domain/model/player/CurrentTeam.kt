package com.example.feature_search_api.domain.model.player


import com.google.gson.annotations.SerializedName

data class CurrentTeam(
    @SerializedName("acronym")
    val acronym: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("location")
    val location: String?,
    @SerializedName("modified_at")
    val modifiedAt: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("slug")
    val slug: String?
)