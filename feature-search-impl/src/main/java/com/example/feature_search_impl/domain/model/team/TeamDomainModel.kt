package com.example.feature_search_impl.domain.model.team

data class TeamDomainModel(
    val acronym: String?,
    val currentVideogame: String?,
    val id: Int?,
    val imageUrl: String?,
    val location: String?,
    val name: String?,
    val players: List<PlayerTeamDomain?>?,
    val slug: String?
)