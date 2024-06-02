package com.example.feature_predict_impl.domain.mapper

import com.example.feature_predict_impl.domain.model.UpcomingMatchesDomainModel
import com.example.feature_predict_impl.presentation.model.MatchPresentationModel
import com.nefrit.common.utils.DateFormatter
import javax.inject.Inject

class FromDomainToPresentationMapper @Inject constructor(
    private val dateFormatter: DateFormatter
) {
    fun mapDomainToPresentation(domainData: UpcomingMatchesDomainModel?): MatchPresentationModel {
        return MatchPresentationModel(
            id = domainData?.id,
            firstTeamImage = domainData?.firstTeam?.imageUrl,
            firstTeamName = domainData?.firstTeam?.name,
            secondTeamImage = domainData?.secondTeam?.imageUrl,
            secondTeamName = domainData?.secondTeam?.name,
            matchType = domainData?.numberOfGames,
            matchTime = dateFormatter.formatDate(domainData?.beginAt!!),
            leagueName = domainData.serieName?:"",
            firstTeamId = domainData.firstTeam?.id,
            secondTeamId = domainData.secondTeam?.id
        )
    }
}