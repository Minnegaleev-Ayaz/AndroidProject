package com.example.feature_predict_impl.di

import com.example.feature_predict_impl.PredictionFeatureRouter
import com.example.firebase_api.di.PredictsReference
import com.example.panda_score_api.remote.ExceptionHandlerDelegate
import com.example.panda_score_api.remote.PandasScoreApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.nefrit.common.di.FeatureApiHolder
import com.nefrit.common.di.FeatureContainer
import com.nefrit.common.di.scope.ApplicationScope
import com.nefrit.common.di.scope.FeatureScope
import javax.inject.Inject

@ApplicationScope
class PredictionFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val router: PredictionFeatureRouter,
    private val api:PandasScoreApi,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
    private val firebaseAuth: FirebaseAuth,
    @PredictsReference
    private val databaseReference: DatabaseReference
) : FeatureApiHolder(featureContainer) {
    override fun initializeDependencies(): Any {
        val predictionFeatureDependencies =
            DaggerPredictionFeatureComponent_PredictionFeatureDependenciesComponent.builder()
                .commonApi(commonApi())
                .build()
        return DaggerPredictionFeatureComponent.builder()
            .withDependencies(predictionFeatureDependencies)
            .exceptionHandlerDelegate(exceptionHandlerDelegate)
            .api(api)
            .firebase(firebaseAuth)
            .predictReference(databaseReference)
            .router(router)
            .build()

    }

}