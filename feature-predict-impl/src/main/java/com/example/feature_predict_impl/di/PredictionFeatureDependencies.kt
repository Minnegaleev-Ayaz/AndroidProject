package com.example.feature_predict_impl.di

import com.nefrit.common.core.resources.ResourceManager
import kotlinx.coroutines.CoroutineDispatcher

interface PredictionFeatureDependencies {
    fun resourceManager(): ResourceManager

    fun corutineDispatcher(): CoroutineDispatcher
}