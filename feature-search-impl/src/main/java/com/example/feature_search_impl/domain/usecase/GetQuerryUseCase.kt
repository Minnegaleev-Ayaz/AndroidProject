package com.example.feature_search_impl.domain.usecase

import com.example.feature_search_api.domain.repository.SearchRepository
import com.example.feature_search_impl.data.mapper.MapFromDataToDomain
import com.example.feature_search_impl.domain.model.querry.QuerryDomainModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetQuerryUseCase @Inject constructor(
    private val dataMapper: MapFromDataToDomain,
    private val repository: SearchRepository,
    private val corutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): List<QuerryDomainModel> {
        return withContext(corutineDispatcher) {
            repository.getQuerries().map { it-> dataMapper.mapQuerryFromDataToDomain(it) }
        }
    }
}