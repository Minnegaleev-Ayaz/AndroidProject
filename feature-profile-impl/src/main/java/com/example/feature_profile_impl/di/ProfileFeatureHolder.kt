package com.example.feature_profile_impl.di

import android.content.Context
import com.example.feature_profile_impl.ProfileFeatureRouter
import com.example.firebase_api.di.UsersReference
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.nefrit.common.di.FeatureApiHolder
import com.nefrit.common.di.FeatureContainer
import javax.inject.Inject

class ProfileFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val usersAuthRouter: ProfileFeatureRouter,
    private val context: Context,
    private val firebaseAuth: FirebaseAuth,
    @UsersReference
    private val usersReference: DatabaseReference
) : FeatureApiHolder(featureContainer) {
    override fun initializeDependencies(): Any {
        val authFeatureDependencies = DaggerProfileFeatureCompomemt_ProfileFeatureDependenciesComponent.builder()
            .commonApi(commonApi())
            .build()
        return DaggerProfileFeatureCompomemt.builder()
            .context(context)
            .firebaseAuth(firebaseAuth)
            .withDependencies(authFeatureDependencies)
            .router(usersAuthRouter)
            .userReferences(usersReference)
            .build()

    }
}