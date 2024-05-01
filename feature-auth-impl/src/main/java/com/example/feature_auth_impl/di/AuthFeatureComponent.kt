package com.example.feature_auth_impl.di

import android.content.Context
import com.example.feature_auth_api.di.AuthFeatureApi
import com.example.feature_auth_impl.UsersAuthRouter
import com.example.feature_auth_impl.presentation.ui.signIn.di.SignInComponent
import com.example.feature_auth_impl.presentation.ui.signUp.di.SignUpComponent
import com.nefrit.common.di.CommonApi
import com.nefrit.common.di.modules.PreferencesModule
import com.nefrit.common.di.scope.FeatureScope
import dagger.BindsInstance
import dagger.Component

@FeatureScope
@Component(
    modules = [
        AuthFeatureModule::class
    ],
    dependencies = [AuthFeatureDependencies::class]
)
interface AuthFeatureComponent : AuthFeatureApi {
    fun signUpComponentFactory(): SignUpComponent.Factory

    fun signInComponentFactory(): SignInComponent.Factory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        @BindsInstance
        fun router(usersAuthRouter: UsersAuthRouter): Builder
        fun withDependencies(deps: AuthFeatureDependencies): Builder
        fun build(): AuthFeatureComponent
    }

    @Component(
        dependencies = [
            CommonApi::class
        ]
    )
    interface AuthFeatureDependenciesComponent : AuthFeatureDependencies

}