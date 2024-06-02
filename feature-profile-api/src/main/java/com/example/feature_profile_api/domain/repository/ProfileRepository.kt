package com.example.feature_profile_api.domain.repository

import com.google.firebase.auth.FirebaseUser

interface ProfileRepository {
    suspend fun getCurrentUser(): FirebaseUser?
}