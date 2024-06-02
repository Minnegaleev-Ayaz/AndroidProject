package com.example.feature_predict_impl.data.mapper

import com.example.feature_predict_api.domain.model.UpcomingMatchesPredictDataModel
import com.example.feature_predict_api.domain.model.helper_models.OpponentData
import com.example.feature_predict_api.domain.model.helper_models.TournamentData
import com.example.feature_predict_impl.data.mapper.helper_models.OpponentDomain
import com.example.feature_predict_impl.domain.model.UpcomingMatchesDomainModel
import com.example.feature_predict_impl.domain.model.helper_models.TournamentDomain
import javax.inject.Inject

class FromDataToDomainMapper @Inject constructor() {
    fun mapDataToDomain(upcomingMatchesDataModel: UpcomingMatchesPredictDataModel): UpcomingMatchesDomainModel? {
        if (upcomingMatchesDataModel.opponents.size==2 ){
            return UpcomingMatchesDomainModel(
                beginAt = upcomingMatchesDataModel.beginAt,
                id = upcomingMatchesDataModel.id,
                matchType = upcomingMatchesDataModel.matchType,
                modifiedAt = upcomingMatchesDataModel.modifiedAt,
                numberOfGames = upcomingMatchesDataModel.games?.size,
                firstTeam = mapOpponentDataToOpponentDomain(upcomingMatchesDataModel.opponents.get(0)),
                secondTeam = mapOpponentDataToOpponentDomain(upcomingMatchesDataModel.opponents.get(1)),
                serieName = upcomingMatchesDataModel.serie?.fullName,
                tournament = mapTournamentDataToTournamentDomain(upcomingMatchesDataModel.tournament),
            )
        }
        return null
    }

    fun mapOpponentDataToOpponentDomain(opponentData: OpponentData): OpponentDomain {
        return OpponentDomain(
            id = opponentData.opponent.id,
            imageUrl = opponentData.opponent.imageUrl,
            location = opponentData.opponent.location,
            modifiedAt = opponentData.opponent.modifiedAt,
            name = opponentData.opponent.name,
            slug = opponentData.opponent.slug
        )
    }

    fun mapTournamentDataToTournamentDomain(tournamentData: TournamentData): TournamentDomain {
        return TournamentDomain(
            beginAt = tournamentData.beginAt,
            endAt = tournamentData.endAt,
            id = tournamentData.id,
            leagueId = tournamentData.leagueId,
            name = tournamentData.name,
            serieId = tournamentData.serieId,
            slug = tournamentData.slug,
            tier = tournamentData.tier
        )
    }
}