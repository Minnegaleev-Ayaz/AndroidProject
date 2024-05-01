package com.example.feature_splash_screen_impl.di

import android.content.Context
import com.example.feature_splash_screen_api.di.SplashScreenApi
import com.example.feature_splash_screen_impl.SplashScreenRouter
import com.example.feature_splash_screen_impl.presentation.di.SplashScreenComponent
import com.nefrit.common.di.CommonApi
import com.nefrit.common.di.scope.ApplicationScope
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    dependencies = [SplashScreenFeatureDependencies::class]
)
interface SplashScreenFeatureComponent : SplashScreenApi {
    fun splashScreenFactory():SplashScreenComponent.Factory
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun router(splashScreenRouter: SplashScreenRouter):Builder
        fun withDependencies(dependencies: SplashScreenFeatureDependencies): Builder
        fun build(): SplashScreenFeatureComponent
    }
    @Component(
        dependencies = [
            CommonApi::class
        ]
    )
    interface SplashScreenFeatureDependenciesComponent:SplashScreenFeatureDependencies
}