package com.example.feature_search_api.domain.repository

import com.example.feature_search_api.domain.model.player.PlayerDataModel
import com.example.feature_search_api.domain.model.team.TeamDataModel
import com.example.room_api.entity.QuerryEntity

interface SearchRepository {
    suspend fun getTeams(name:String):List<TeamDataModel>
    suspend fun getPlayers(name:String):List<PlayerDataModel>
    fun getQuerries():List<QuerryEntity>
    fun saveQuerry(text:String):QuerryEntity
    fun delQuerry(id:Int)
}