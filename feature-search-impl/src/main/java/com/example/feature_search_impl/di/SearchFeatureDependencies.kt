package com.example.feature_search_impl.di

import com.nefrit.common.core.resources.ResourceManager
import kotlinx.coroutines.CoroutineDispatcher

interface SearchFeatureDependencies {
    fun resourceManager(): ResourceManager

    fun corutineDispatcher(): CoroutineDispatcher
}