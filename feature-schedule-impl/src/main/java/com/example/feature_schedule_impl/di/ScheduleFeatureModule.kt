package com.example.feature_schedule_impl.di

import com.example.feature_schedule_api.domain.repository.ScheduleRepository
import com.example.feature_schedule_impl.data.repository.ScheduleRepositoryImpl
import com.nefrit.common.di.scope.FeatureScope
import dagger.Module
import dagger.Provides
@Module
class ScheduleFeatureModule {
    @Provides
    @FeatureScope
    fun providePredictionRepository(scheduleRepositoryImpl: ScheduleRepositoryImpl): ScheduleRepository = scheduleRepositoryImpl

}