package com.example.feature_profile_impl.domain.usecase

import com.example.feature_profile_api.domain.repository.ProfileRepository
import com.example.feature_profile_api.domain.repository.model.DataUser
import com.nefrit.common.utils.AsyncResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: ProfileRepository,
    private val corutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(flow: MutableStateFlow<AsyncResult<DataUser>?>) {
        return withContext(corutineDispatcher) {
            repository.getCurrentUser(flow)
        }
    }
}