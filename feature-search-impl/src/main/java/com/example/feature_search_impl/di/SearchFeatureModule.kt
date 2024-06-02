package com.example.feature_search_impl.di

import com.example.feature_search_api.domain.repository.SearchRepository
import com.example.feature_search_impl.data.repository.SearchRepositoryImpl
import com.nefrit.common.di.scope.FeatureScope
import dagger.Module
import dagger.Provides
@Module
class SearchFeatureModule {
    @Provides
    @FeatureScope
    fun providePredictionRepository(scheduleRepositoryImpl: SearchRepositoryImpl): SearchRepository = scheduleRepositoryImpl
}