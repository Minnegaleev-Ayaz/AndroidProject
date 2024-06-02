package com.example.feature_search_impl

import com.example.feature_search_impl.presentation.model.player.PlayerUiModel
import com.example.feature_search_impl.presentation.model.team.PlayerUi
import com.example.feature_search_impl.presentation.model.team.TeamUiModel

interface SearchFeatureRouter {
    fun openPlayerFromSearch(model:PlayerUiModel)
    fun openTeamFromSearch(model:TeamUiModel)
    fun openPlayerFromTeam(model: PlayerUi)
}