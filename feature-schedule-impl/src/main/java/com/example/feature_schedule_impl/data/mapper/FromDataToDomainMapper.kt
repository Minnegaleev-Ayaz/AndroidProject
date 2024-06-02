package com.example.feature_schedule_impl.data.mapper

import com.example.feature_schedule_api.domain.model.MatchesDataModel
import com.example.feature_schedule_api.domain.model.OpponentX
import com.example.feature_schedule_api.domain.model.Streams
import com.example.feature_schedule_api.domain.model.Tournament
import com.example.feature_schedule_impl.domain.model.OpponentDomain

import com.example.feature_schedule_impl.domain.model.MatchesDomainModel
import com.example.feature_schedule_impl.domain.model.StreamDomainModel
import com.example.feature_schedule_impl.domain.model.TournamentDomain
import javax.inject.Inject

class FromDataToDomainMapper @Inject constructor() {
    fun mapDataToDomain(matchesDataModel: MatchesDataModel): MatchesDomainModel? {
        return if (matchesDataModel.opponents.size == 2) {
            MatchesDomainModel(
                beginAt = matchesDataModel.beginAt,
                id = matchesDataModel.id,
                matchType = matchesDataModel.matchType,
                modifiedAt = matchesDataModel.modifiedAt,
                numberOfGames = matchesDataModel.numberOfGames,
                firstTeam = mapOpponentDataToOpponentDomain(matchesDataModel.opponents[0].opponent),
                secondTeam = mapOpponentDataToOpponentDomain(matchesDataModel.opponents[1].opponent),
                serieName = matchesDataModel.serie?.fullName,
                tournament = mapTournamentDataToTournamentDomain(matchesDataModel.tournament),
                streams = matchesDataModel.streamsList.map { it -> mapStreamDataToDomain(it) },
                firstTeamResult = matchesDataModel.results.get(0).score,
                secondTeamResult = matchesDataModel.results.get(1).score,
                endAt = matchesDataModel.endAt
            )
        }else{
            null
        }
    }

    fun mapOpponentDataToOpponentDomain(opponentData: OpponentX): OpponentDomain {
        return OpponentDomain(
            id = opponentData.id,
            imageUrl = opponentData.imageUrl,
            location = opponentData.location,
            modifiedAt = opponentData.modifiedAt,
            name = opponentData.name,
            slug = opponentData.slug
        )
    }

    fun mapTournamentDataToTournamentDomain(tournamentData: Tournament): TournamentDomain {
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

    fun mapStreamDataToDomain(stream: Streams): StreamDomainModel {
        return StreamDomainModel(
            embedUrl = stream.embedUrl,
            language = stream.language,
            main = stream.main,
            official = stream.official,
            rawUrl = stream.rawUrl,
        )
    }

}