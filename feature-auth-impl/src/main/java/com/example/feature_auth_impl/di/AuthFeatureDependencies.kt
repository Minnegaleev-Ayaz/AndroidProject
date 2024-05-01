package com.example.feature_auth_impl.di

import com.nefrit.common.core.preferences.Preferences
import com.nefrit.common.core.resources.ResourceManager
import kotlinx.coroutines.CoroutineDispatcher

interface AuthFeatureDependencies {
    fun resourceManager(): ResourceManager

    fun corutineDispatcher():CoroutineDispatcher
    fun preferences():Preferences
}