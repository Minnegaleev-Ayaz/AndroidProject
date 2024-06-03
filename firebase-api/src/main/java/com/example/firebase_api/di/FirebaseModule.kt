package com.example.firebase_api.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.nefrit.common.di.scope.ApplicationScope
import com.nefrit.common.di.scope.FeatureScope
import dagger.Module
import dagger.Provides

@Module
class FirebaseModule {
    @Provides
    @ApplicationScope
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
    @Provides
    @UsersReference
    @ApplicationScope
    fun provideUsersReference():DatabaseReference = FirebaseDatabase.getInstance().getReference("user")

    @Provides
    @PredictsReference
    @ApplicationScope
    fun providePredictsReference():DatabaseReference = FirebaseDatabase.getInstance().getReference("predict")
}