package com.example.feature_search_impl.data.repository

import com.example.feature_search_api.domain.model.player.PlayerDataModel
import com.example.feature_search_api.domain.model.team.TeamDataModel
import com.example.feature_search_api.domain.repository.SearchRepository
import com.example.panda_score_api.remote.PandasScoreApi
import com.example.room_api.AppDatabase
import com.example.room_api.entity.QuerryEntity
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val api: PandasScoreApi,
    private val appDatabase: AppDatabase

) : SearchRepository {
    override suspend fun getTeams(name: String): List<TeamDataModel> {
        return api.getTeams(name)
    }

    override suspend fun getPlayers(name:String): List<PlayerDataModel> {
        return api.getPlayers(name)
    }

    override fun getQuerries(): List<QuerryEntity> {
        return appDatabase.myQuerryDao().getAll()
    }

    override fun saveQuerry(text: String):QuerryEntity {
        val res = QuerryEntity(text=text)
        appDatabase.myQuerryDao().insert(res)
        return res
    }

    override fun delQuerry(id: Int) {
        appDatabase.myQuerryDao().delete(id)
    }
}