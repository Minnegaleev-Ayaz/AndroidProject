package com.example.feature_search_api.domain.model.player


import com.google.gson.annotations.SerializedName

data class PlayerDataModel(
    @SerializedName("active")
    val active: Boolean?,
    @SerializedName("current_team")
    val currentTeam: CurrentTeam?,
    @SerializedName("current_videogame")
    val currentVideogame: CurrentVideogame?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("modified_at")
    val modifiedAt: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("nationality")
    val nationality: String?,
    @SerializedName("role")
    val role: String?,
    @SerializedName("slug")
    val slug: String?
)