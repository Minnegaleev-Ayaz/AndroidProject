package com.example.feature_profile_impl.di

import android.content.Context
import com.example.feature_profile_impl.ProfileFeatureRouter
import com.google.firebase.auth.FirebaseAuth
import com.nefrit.common.di.FeatureApiHolder
import com.nefrit.common.di.FeatureContainer
import javax.inject.Inject

class ProfileFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val usersAuthRouter: ProfileFeatureRouter,
    private val context: Context,
    private val firebaseAuth: FirebaseAuth
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
            .build()

    }
}