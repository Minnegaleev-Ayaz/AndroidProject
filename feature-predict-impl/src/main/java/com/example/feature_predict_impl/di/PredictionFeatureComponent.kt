package com.example.feature_predict_impl.di

import com.example.feature_predict_api.di.PredictFeatureApi
import com.example.feature_predict_impl.PredictionFeatureRouter
import com.example.feature_predict_impl.data.di.PredictNetworkModule
import com.example.feature_predict_impl.presentation.ui.prediction.di.PredictComponent
import com.nefrit.common.di.CommonApi
import com.nefrit.common.di.modules.CommonModule
import com.nefrit.common.di.scope.FeatureScope
import dagger.BindsInstance
import dagger.Component

@FeatureScope
@Component(
    modules = [PredictionFeatureModule::class, PredictNetworkModule::class],
    dependencies = [PredictionFeatureDependencies::class]
)
interface PredictionFeatureComponent : PredictFeatureApi {
    fun predictionComponentFactory(): PredictComponent.Factory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun router(predictionFeatureRouter: PredictionFeatureRouter): Builder
        fun withDependencies(deps: PredictionFeatureDependencies): Builder
        fun build(): PredictionFeatureComponent
    }

    @Component(
        dependencies = [
            CommonApi::class
        ]
    )
    interface PredictionFeatureDependenciesComponent : PredictionFeatureDependencies
}