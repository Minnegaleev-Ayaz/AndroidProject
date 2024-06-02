package com.example.feature_search_impl.data.mapper

import com.example.feature_search_api.domain.model.player.CurrentTeam
import com.example.feature_search_api.domain.model.player.PlayerDataModel
import com.example.feature_search_api.domain.model.team.Player
import com.example.feature_search_api.domain.model.team.TeamDataModel
import com.example.feature_search_impl.domain.model.player.PlayerDomainModel
import com.example.feature_search_impl.domain.model.team.PlayerTeamDomain
import com.example.feature_search_impl.domain.model.querry.QuerryDomainModel
import com.example.feature_search_impl.domain.model.team.TeamDomainModel
import com.example.feature_search_impl.domain.model.player.TeamPlayerDomain
import com.example.room_api.entity.QuerryEntity
import javax.inject.Inject

class MapFromDataToDomain @Inject constructor(){
    fun mapTeamFromDataToDomain(dataModel: TeamDataModel): TeamDomainModel {
        return TeamDomainModel(
            acronym = dataModel.acronym,
            currentVideogame = dataModel.currentVideogame?.name,
            id = dataModel.id,
            imageUrl = dataModel.imageUrl,
            location = dataModel.location,
            name = dataModel.name,
            players = dataModel.players?.filterNotNull()?.map { it-> mapPlayerToDomain(it) },
            slug = dataModel.slug
        )
    }
    fun mapPlayerFromDataToDomain(dataModel:PlayerDataModel): PlayerDomainModel {
        return PlayerDomainModel(
            active = dataModel.active,
            currentTeam = dataModel.currentTeam?.let { mapTeamToDomain(it) },
            currentVideogame = dataModel.currentVideogame?.name,
            firstName = dataModel.firstName,
            id = dataModel.id,
            imageUrl = dataModel.imageUrl,
            lastName = dataModel.lastName,
            name = dataModel.name,
            nationality = dataModel.nationality,
            role = dataModel.role,
            slug = dataModel.slug

        )
    }
    fun mapQuerryFromDataToDomain(dataModel:QuerryEntity): QuerryDomainModel {
        return QuerryDomainModel(
            dataModel.id,
            dataModel.text
        )
    }
    fun mapPlayerToDomain(player: Player): PlayerTeamDomain {
        return PlayerTeamDomain(
            active = player.active,
            age = player.age,
            birthday = player.birthday,
            firstName = player.firstName,
            id = player.id,
            imageUrl = player.imageUrl,
            lastName = player.lastName,
            name = player.name,
            nationality = player.nationality,
            role = player.role,
            slug = player.slug
        )
    }
    fun mapTeamToDomain(team:CurrentTeam): TeamPlayerDomain {
        return TeamPlayerDomain(
            acronym = team.acronym,
            id = team.id,
            imageUrl = team.imageUrl,
            location = team.location,
            name = team.name,
            slug = team.slug
        )
    }
}