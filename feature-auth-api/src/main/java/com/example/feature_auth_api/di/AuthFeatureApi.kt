package com.example.feature_auth_api.di

import android.content.Context
import com.example.feature_auth_impl.domain.repository.UserRepository
import com.nefrit.common.core.preferences.Preferences

interface AuthFeatureApi {
    fun provideUserRepository(): UserRepository
    fun providePreferences():Preferences
    fun applicationContext(): Context
}