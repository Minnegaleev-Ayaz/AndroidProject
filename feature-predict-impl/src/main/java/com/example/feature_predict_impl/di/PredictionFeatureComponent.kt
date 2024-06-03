package com.example.feature_predict_impl.di

import android.app.Activity
import androidx.activity.ComponentActivity
import com.example.feature_predict_api.di.PredictFeatureApi
import com.example.feature_predict_impl.PredictionFeatureRouter
import com.example.feature_predict_impl.presentation.ui.prediction.bottom.di.PredictionBottomComponent
import com.example.feature_predict_impl.presentation.ui.prediction.prediction.di.PredictComponent
import com.example.firebase_api.di.PredictsReference
import com.example.panda_score_api.remote.ExceptionHandlerDelegate
import com.example.panda_score_api.remote.PandasScoreApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.nefrit.common.base.BaseActivity
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
    fun predictBottomComponentFactory():PredictionBottomComponent.Factory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun api(pandasScoreApi: PandasScoreApi): Builder
        @BindsInstance
        fun exceptionHandlerDelegate(exceptionHandlerDelegate: ExceptionHandlerDelegate):Builder
        @BindsInstance
        fun router(predictionFeatureRouter: PredictionFeatureRouter): Builder
        @BindsInstance
        fun firebase(firebaseAuth: FirebaseAuth):Builder
        @BindsInstance
        @PredictsReference
        fun predictReference(databaseReference: DatabaseReference):Builder

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