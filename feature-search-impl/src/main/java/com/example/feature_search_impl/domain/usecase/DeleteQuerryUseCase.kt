package com.example.feature_search_impl.domain.usecase

import com.example.feature_search_api.domain.repository.SearchRepository
import com.example.feature_search_impl.presentation.model.querry.QuerryUiModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteQuerryUseCase @Inject constructor(
    private val repository: SearchRepository,
    private val corutineDispatcher: CoroutineDispatcher
){
    suspend operator fun invoke(querryUiModel: QuerryUiModel){
        withContext(corutineDispatcher){
            repository.delQuerry(querryUiModel.id)
        }
    }
}