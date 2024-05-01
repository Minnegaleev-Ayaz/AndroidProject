package com.example.feature_predict_impl.domain.model

import com.example.feature_predict_impl.data.mapper.helper_models.OpponentDomain
import com.example.feature_predict_impl.domain.model.helper_models.TournamentDomain

data class UpcomingMatchesDomainModel(
    val beginAt: String?,
    val id: Int?,
    val matchType: String?,
    val modifiedAt: String?,
    val numberOfGames: Int?,
    val firstTeam: OpponentDomain?,
    val secondTeam:OpponentDomain?,
    val serieName: String?,
    val tournament: TournamentDomain?,
)