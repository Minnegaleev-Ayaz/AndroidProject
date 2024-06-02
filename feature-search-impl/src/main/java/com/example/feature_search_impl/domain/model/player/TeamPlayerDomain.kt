package com.example.feature_search_impl.domain.model.player

import com.google.gson.annotations.SerializedName

data class TeamPlayerDomain(
    val acronym: String?,
    val id: Int?,
    val imageUrl: String?,
    val location: String?,
    val name: String?,
    val slug: String?
)