package com.example.feature_splash_screen_impl.di

import android.content.Context
import com.example.feature_splash_screen_impl.SplashScreenRouter
import com.nefrit.common.di.CommonApi
import com.nefrit.common.di.FeatureApiHolder
import com.nefrit.common.di.FeatureContainer
import com.nefrit.common.di.scope.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class SplashScreenFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val splashScreenRouter: SplashScreenRouter,
    private val context: Context
    ) : FeatureApiHolder(featureContainer) {
    override fun initializeDependencies(): Any {
        val deps =
            DaggerSplashScreenFeatureComponent_SplashScreenFeatureDependenciesComponent.builder()
                .commonApi(commonApi())
                .build()
        return DaggerSplashScreenFeatureComponent.builder()
            .withDependencies(deps)
            .router(splashScreenRouter)
            .build()
    }
}