package com.nefrit.app.di.deps

import com.example.feature_auth_api.di.AuthFeatureApi
import com.example.feature_auth_impl.di.AuthFeatureHolder
import com.nefrit.app.App
import com.nefrit.common.di.FeatureApiHolder
import com.nefrit.common.di.FeatureContainer
import com.nefrit.common.di.scope.ApplicationScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
interface ComponentHolderModule {

    @ApplicationScope
    @Binds
    fun provideFeatureContainer(application: App): FeatureContainer

    @ApplicationScope
    @Binds
    @ClassKey(AuthFeatureApi::class)
    @IntoMap
    fun provideUserFeatureHolder(userFeatureHolder: AuthFeatureHolder): FeatureApiHolder

}