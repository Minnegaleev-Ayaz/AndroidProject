package com.example.feature_search_impl.domain.usecase

import com.example.feature_search_api.domain.repository.SearchRepository
import com.example.feature_search_impl.data.mapper.MapFromDataToDomain
import com.example.feature_search_impl.domain.model.player.PlayerDomainModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPlayersUseCase @Inject constructor(
    private val dataMapper: MapFromDataToDomain,
    private val repository: SearchRepository,
    private val corutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(name:String): List<PlayerDomainModel> {
        return withContext(corutineDispatcher) {
            repository.getPlayers(name).map { it-> dataMapper.mapPlayerFromDataToDomain(it) }
        }
    }
}