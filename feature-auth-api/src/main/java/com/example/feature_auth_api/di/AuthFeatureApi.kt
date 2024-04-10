package com.example.feature_auth_api.di

import com.example.feature_auth_impl.domain.repository.UserRepository

interface AuthFeatureApi {
    fun provideUserRepository(): UserRepository
}