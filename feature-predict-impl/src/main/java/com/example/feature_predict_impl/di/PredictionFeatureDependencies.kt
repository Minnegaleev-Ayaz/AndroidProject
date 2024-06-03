package com.example.feature_predict_impl.di

import com.example.panda_score_api.remote.PandasScoreApi
import com.nefrit.common.core.preferences.Preferences
import com.nefrit.common.core.resources.ResourceManager
import com.nefrit.common.utils.DateFormatter
import kotlinx.coroutines.CoroutineDispatcher

interface PredictionFeatureDependencies {
    fun resourceManager(): ResourceManager

    fun corutineDispatcher(): CoroutineDispatcher
    fun dateFormatter():DateFormatter
    fun preferences():Preferences
}