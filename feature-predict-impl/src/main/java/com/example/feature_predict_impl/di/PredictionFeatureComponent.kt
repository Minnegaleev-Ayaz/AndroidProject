package com.example.feature_predict_impl.di

import com.example.feature_predict_api.di.PredictFeatureApi
import com.example.feature_predict_impl.PredictionFeatureRouter
import com.example.feature_predict_impl.presentation.ui.prediction.di.PredictComponent
import com.example.panda_score_api.remote.PandasScoreApi
import com.nefrit.common.di.CommonApi
import com.nefrit.common.di.scope.FeatureScope
import dagger.BindsInstance
import dagger.Component

@FeatureScope
@Component(
    modules = [PredictionFeatureModule::class],
    dependencies = [PredictionFeatureDependencies::class]
)
interface PredictionFeatureComponent : PredictFeatureApi {
    fun predictionComponentFactory(): PredictComponent.Factory

    @Component.Builder
    interface Builder {
        @BindsInstance
         fun api(pandasScoreApi: PandasScoreApi):Builder
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