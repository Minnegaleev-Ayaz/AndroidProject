package com.example.feature_predict_impl.domain.usecases

import com.example.feature_predict_api.domain.repository.PredictionRepository
import com.example.feature_predict_impl.data.mapper.FromDataToDomainMapper
import com.example.feature_predict_impl.domain.mapper.FromDomainToPresentationMapper
import com.example.feature_predict_impl.domain.model.UpcomingMatchesDomainModel
import com.example.feature_predict_impl.presentation.model.MatchPresentationModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpcomingMatchesUseCase @Inject constructor(
    private val dataMapper: FromDataToDomainMapper,
    private val domainMapper:FromDomainToPresentationMapper,
    private val repository: PredictionRepository,
    private val corutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): List<UpcomingMatchesDomainModel> {
        return withContext(corutineDispatcher) {
            repository.getUpcomingMatches().map { dataMapper.mapDataToDomain(it)}.filterNotNull()
        }
    }
}