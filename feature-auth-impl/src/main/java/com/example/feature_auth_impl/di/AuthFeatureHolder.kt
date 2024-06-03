package com.example.feature_auth_impl.di

import android.content.Context
import com.example.feature_auth_impl.UsersAuthRouter
import com.example.firebase_api.di.UsersReference
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.nefrit.common.di.FeatureApiHolder
import com.nefrit.common.di.FeatureContainer
import com.nefrit.common.di.scope.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class AuthFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val usersAuthRouter: UsersAuthRouter,
    private val context:Context,
    private val firebaseAuth: FirebaseAuth,
    @UsersReference
    private val databaseReference: DatabaseReference
) : FeatureApiHolder(featureContainer) {
    override fun initializeDependencies(): Any {
        val authFeatureDependencies = DaggerAuthFeatureComponent_AuthFeatureDependenciesComponent.builder()
            .commonApi(commonApi())
            .build()
        return DaggerAuthFeatureComponent.builder()
            .context(context)
            .firebaseAuth(firebaseAuth)
            .withDependencies(authFeatureDependencies)
            .databaseReference(databaseReference)
            .router(usersAuthRouter)
            .build()

    }


}
