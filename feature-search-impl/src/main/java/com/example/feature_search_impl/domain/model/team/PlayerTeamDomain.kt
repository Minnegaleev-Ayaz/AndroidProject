package com.example.feature_search_impl.domain.model.team

import com.google.gson.annotations.SerializedName

data class PlayerTeamDomain(
    val active: Boolean?,
    val age: Int?,
    val birthday: String?,
    val firstName: String?,
    val id: Int?,
    val imageUrl: String?,
    val lastName: String?,
    val name: String?,
    val nationality: String?,
    val role: String?,
    val slug: String?
)
