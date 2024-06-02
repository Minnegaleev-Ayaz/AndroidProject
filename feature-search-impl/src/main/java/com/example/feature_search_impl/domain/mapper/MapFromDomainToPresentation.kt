package com.example.feature_search_impl.domain.mapper

import com.example.feature_search_api.domain.model.team.TeamDataModel
import com.example.feature_search_impl.domain.model.player.PlayerDomainModel
import com.example.feature_search_impl.domain.model.querry.QuerryDomainModel
import com.example.feature_search_impl.domain.model.team.PlayerTeamDomain
import com.example.feature_search_impl.domain.model.team.TeamDomainModel
import com.example.feature_search_impl.presentation.model.player.PlayerUiModel
import com.example.feature_search_impl.presentation.model.querry.QuerryUiModel
import com.example.feature_search_impl.presentation.model.team.PlayerUi
import com.example.feature_search_impl.presentation.model.team.TeamUiModel
import com.example.room_api.entity.QuerryEntity
import javax.inject.Inject

class MapFromDomainToPresentation @Inject constructor() {
    fun mapTeamFromDomainToUi(dataModel: TeamDomainModel): TeamUiModel {
        return TeamUiModel(
            acronym = dataModel.acronym,
            id = dataModel.id,
            imageUrl = dataModel.imageUrl,
            location = dataModel.location,
            name = dataModel.name,
            players = dataModel.players?.filterNotNull()?.map { it-> mapPlayerToUi(it) },
            slug = dataModel.slug
        )
    }
    fun mapPlayerFromDomainToUi(dataModel: PlayerDomainModel): PlayerUiModel {
        return PlayerUiModel(
            active = dataModel.active,
            firstName = dataModel.firstName,
            id = dataModel.id,
            imageUrl = dataModel.imageUrl,
            lastName = dataModel.lastName,
            name = dataModel.name,
            nationality = dataModel.nationality,
            role = dataModel.role,
            slug = dataModel.slug,
            teamImageUrl = dataModel.currentTeam?.imageUrl,
            teamName = dataModel.currentTeam?.slug,
        )
    }
    fun mapQuerryFromDomainToUi(dataModel: QuerryDomainModel): QuerryUiModel {
        return QuerryUiModel(
            dataModel.id,
            dataModel.querry_text
        )
    }
    fun mapPlayerToUi(player: PlayerTeamDomain): PlayerUi {
        return PlayerUi(
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
}