package com.example.feature_profile_api.domain.repository

import com.example.feature_profile_api.domain.repository.model.DataUser
import com.nefrit.common.utils.AsyncResult
import kotlinx.coroutines.flow.MutableStateFlow

interface ProfileRepository {
    suspend fun getCurrentUser(flow: MutableStateFlow<AsyncResult<DataUser>?>)
}