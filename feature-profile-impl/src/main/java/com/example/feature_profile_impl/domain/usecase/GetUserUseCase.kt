package com.example.feature_profile_impl.domain.usecase

import com.example.feature_profile_api.domain.repository.ProfileRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: ProfileRepository,
    private val corutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): FirebaseUser? {
        return withContext(corutineDispatcher) {
            repository.getCurrentUser()
        }
    }
}