package com.example.feature_schedule_impl.domain.usecases


import com.example.feature_schedule_api.domain.repository.ScheduleRepository
import com.example.feature_schedule_impl.data.mapper.FromDataToDomainMapper
import com.example.feature_schedule_impl.domain.model.MatchesDomainModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MatchesUseCase @Inject constructor(
    private val dataMapper: FromDataToDomainMapper,
    private val repository: ScheduleRepository,
    private val corutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(matchType:String): List<MatchesDomainModel> {
        return withContext(corutineDispatcher) {
            repository.getMatches(matchType).map { it-> dataMapper.mapDataToDomain(it) }.filterNotNull()
        }
    }
}