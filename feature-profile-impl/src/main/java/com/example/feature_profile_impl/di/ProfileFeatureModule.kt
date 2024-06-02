package com.example.feature_profile_impl.di

import com.example.feature_profile_api.domain.repository.ProfileRepository
import com.example.feature_profile_impl.data.repository.ProfileRepositoryImpl
import com.nefrit.common.di.scope.FeatureScope
import dagger.Module
import dagger.Provides

@Module
class ProfileFeatureModule {
    @Provides
    @FeatureScope
    fun provideProfileRepository(profileRepositoryImpl: ProfileRepositoryImpl): ProfileRepository = profileRepositoryImpl

}