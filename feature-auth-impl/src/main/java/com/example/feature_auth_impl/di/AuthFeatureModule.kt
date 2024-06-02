package com.example.feature_auth_impl.di

import com.example.feature_auth_impl.data.repository.UserRepositoryImpl
import com.example.feature_auth_impl.domain.repository.UserRepository
import com.nefrit.common.di.scope.FeatureScope
import dagger.Module
import dagger.Provides

@Module
class AuthFeatureModule {
    @Provides
    @FeatureScope
    fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository = userRepositoryImpl

}