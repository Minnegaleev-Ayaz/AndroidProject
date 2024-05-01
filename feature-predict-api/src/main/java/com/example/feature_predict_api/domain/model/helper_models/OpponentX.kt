package com.example.feature_predict_api.domain.model.helper_models


import com.google.gson.annotations.SerializedName

data class OpponentX(
    @SerializedName("acronym")
    val acronym: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("location")
    val location: String?,
    @SerializedName("modified_at")
    val modifiedAt: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String?
)