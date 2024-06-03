package com.example.feature_schedule_impl.domain.model

data class MatchesDomainModel (
    val beginAt: String?,
    val id: Int?,
    val matchType: Int,
    val modifiedAt: String?,
    val numberOfGames: Int?,
    val firstTeam: OpponentDomain?,
    val secondTeam:OpponentDomain?,
    val serieName: String?,
    val endAt:String?,
    val tournament: TournamentDomain?,
    val streams:List<StreamDomainModel>,
    val firstTeamResult: Int?,
    val secondTeamResult: Int?
)