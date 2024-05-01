package com.example.feature_auth_impl.di

import com.example.feature_auth_impl.data.repository.UserRepositoryImpl
import com.example.feature_auth_impl.domain.repository.UserRepository
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.actionCodeSettings
import com.nefrit.common.di.scope.FeatureScope
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class AuthFeatureModule {
    @Provides
    @FeatureScope
    fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository = userRepositoryImpl

    @Provides
    @FeatureScope
    fun provideFirebaseAuth():FirebaseAuth = FirebaseAuth.getInstance()

}