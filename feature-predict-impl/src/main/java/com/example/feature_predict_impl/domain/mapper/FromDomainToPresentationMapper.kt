package com.example.feature_predict_impl.domain.mapper

import com.example.feature_predict_impl.domain.model.UpcomingMatchesDomainModel
import com.example.feature_predict_impl.presentation.model.MatchPresentationModel
import javax.inject.Inject

class FromDomainToPresentationMapper @Inject constructor() {
    fun mapDomainToPresentation(domainData: UpcomingMatchesDomainModel?): MatchPresentationModel {
        return MatchPresentationModel(
            id = domainData?.id,
            firstTeamImage = domainData?.firstTeam?.imageUrl,
            firstTeamName = domainData?.firstTeam?.name,
            secondTeamImage = domainData?.secondTeam?.imageUrl,
            secondTeamName = domainData?.secondTeam?.name,
            matchType = domainData?.matchType,
            matchTime = domainData?.beginAt,
            leagueName = domainData?.tournament?.name
        )
    }
}