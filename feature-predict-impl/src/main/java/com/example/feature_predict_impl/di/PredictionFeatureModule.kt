package com.example.feature_predict_impl.di

import com.example.feature_predict_api.domain.repository.PredictionRepository
import com.example.feature_predict_impl.data.repository.PredictionRepositoryImpl
import com.nefrit.common.di.scope.FeatureScope
import dagger.Module
import dagger.Provides
@Module
class PredictionFeatureModule {
    @Provides
    @FeatureScope
    fun providePredictionRepository(predictionRepositoryImpl: PredictionRepositoryImpl): PredictionRepository = predictionRepositoryImpl

}