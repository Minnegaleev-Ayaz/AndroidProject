package com.example.feature_predict_impl.di

import com.example.feature_predict_impl.PredictionFeatureRouter
import com.nefrit.common.di.FeatureApiHolder
import com.nefrit.common.di.FeatureContainer
import com.nefrit.common.di.scope.ApplicationScope
import com.nefrit.common.di.scope.FeatureScope
import javax.inject.Inject

@ApplicationScope
class PredictionFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val router: PredictionFeatureRouter
) : FeatureApiHolder(featureContainer) {
    override fun initializeDependencies(): Any {
        val predictionFeatureDependencies =
            DaggerPredictionFeatureComponent_PredictionFeatureDependenciesComponent.builder()
                .commonApi(commonApi())
                .build()
        return DaggerPredictionFeatureComponent.builder()
            .withDependencies(predictionFeatureDependencies)
            .router(router)
            .build()

    }

}