package com.example.feature_profile_impl.di

import android.content.Context
import com.example.feature_profile_api.di.ProfileFeatureApi
import com.example.feature_profile_impl.ProfileFeatureRouter
import com.example.feature_profile_impl.presentation.ui.profile.di.ProfileComponent
import com.google.firebase.auth.FirebaseAuth
import com.nefrit.common.di.CommonApi
import com.nefrit.common.di.scope.FeatureScope
import dagger.BindsInstance
import dagger.Component
@FeatureScope
@Component(
    modules = [ProfileFeatureModule::class],
    dependencies = [ProfileFeatureDependencies::class]
)
interface ProfileFeatureCompomemt : ProfileFeatureApi {
    fun profileComponentFactory(): ProfileComponent.Factory

    //fun signInComponentFactory(): SignInComponent.Factory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        @BindsInstance
        fun router(usersAuthRouter: ProfileFeatureRouter): Builder

        @BindsInstance
        fun firebaseAuth(firebaseAuth: FirebaseAuth): Builder
        fun withDependencies(deps: ProfileFeatureDependencies): Builder
        fun build(): ProfileFeatureCompomemt
    }

    @Component(
        dependencies = [
            CommonApi::class
        ]
    )
    interface ProfileFeatureDependenciesComponent : ProfileFeatureDependencies
}