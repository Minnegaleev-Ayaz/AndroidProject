package com.example.feature_profile_impl.di

import com.nefrit.common.core.preferences.Preferences
import com.nefrit.common.core.resources.ResourceManager
import kotlinx.coroutines.CoroutineDispatcher

interface ProfileFeatureDependencies {
    fun resourceManager(): ResourceManager

    fun corutineDispatcher(): CoroutineDispatcher
    fun preferences(): Preferences
}