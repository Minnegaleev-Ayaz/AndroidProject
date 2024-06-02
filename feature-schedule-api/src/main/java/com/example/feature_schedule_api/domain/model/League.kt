package com.example.feature_schedule_api.domain.model


import com.google.gson.annotations.SerializedName

data class League(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("modified_at")
    val modifiedAt: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("url")
    val url: String?
)