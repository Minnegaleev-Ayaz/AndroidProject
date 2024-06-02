package com.example.feature_search_impl.domain.model.player


data class PlayerDomainModel(
    val active: Boolean?,
    val currentTeam: TeamPlayerDomain?,
    val currentVideogame: String?,
    val firstName: String?,
    val id: Int?,
    val imageUrl: String?,
    val lastName: String?,
    val name: String?,
    val nationality: String?,
    val role: String?,
    val slug: String?
)