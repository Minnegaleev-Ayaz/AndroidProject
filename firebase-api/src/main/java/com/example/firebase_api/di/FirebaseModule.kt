package com.example.firebase_api.di

import com.google.firebase.auth.FirebaseAuth
import com.nefrit.common.di.scope.ApplicationScope
import com.nefrit.common.di.scope.FeatureScope
import dagger.Module
import dagger.Provides

@Module
class FirebaseModule {
    @Provides
    @ApplicationScope
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
}