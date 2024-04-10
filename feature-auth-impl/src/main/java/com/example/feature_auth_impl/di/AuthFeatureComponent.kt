package com.example.feature_auth_impl.di

import com.example.feature_auth_api.di.AuthFeatureApi
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AuthFeatureModule::class],
    dependencies = [AuthFeatureDependencies::class])
interface AuthFeatureComponent: AuthFeatureApi {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun withDependencies(deps: AuthFeatureDependencies): Builder
        fun build(): AuthFeatureComponent
    }
}