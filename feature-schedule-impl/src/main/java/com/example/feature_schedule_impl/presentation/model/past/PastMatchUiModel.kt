package com.example.feature_schedule_impl.presentation.model.past

data class PastMatchUiModel(
    val id: Int?,
    val firstTeamId: Int?,
    val secondTeamId: Int?,
    val firstTeamName: String?,
    val secondTeamName: String?,
    val firstTeamImage: String?,
    val secondTeamImage: String?,
    val matchType: String?,
    val matchTime: String?,
    val leagueName: String?,
    val firstTeamScore: Int?,
    val secondTeamScore:Int?
)