package com.example.feature_search_impl.domain.usecase

import com.example.feature_search_api.domain.repository.SearchRepository
import com.example.feature_search_impl.data.mapper.MapFromDataToDomain
import com.example.feature_search_impl.domain.model.team.TeamDomainModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetTeamsUseCase @Inject constructor(
    private val dataMapper: MapFromDataToDomain,
    private val repository: SearchRepository,
    private val corutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(name:String): List<TeamDomainModel> {
        return withContext(corutineDispatcher) {
            repository.getTeams(name).map { it-> dataMapper.mapTeamFromDataToDomain(it) }
        }
    }
}