package com.example.feature_schedule_impl.di

import android.content.Intent
import com.example.feature_schedule_impl.ScheduleFeatureRouter
import com.example.panda_score_api.remote.ExceptionHandlerDelegate
import com.example.panda_score_api.remote.PandasScoreApi
import com.nefrit.common.di.FeatureApiHolder
import com.nefrit.common.di.FeatureContainer
import com.nefrit.common.di.scope.ApplicationScope
import com.nefrit.common.notification.NotificationManagerWrapper
import com.nefrit.common.utils.DateFormatter
import javax.inject.Inject

@ApplicationScope
class ScheduleFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val router: ScheduleFeatureRouter,
    private val api:PandasScoreApi,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
    private val intent: Intent,
) : FeatureApiHolder(featureContainer) {
    override fun initializeDependencies(): Any {
        val predictionFeatureDependencies =
            DaggerScheduleFeatureComponent_ScheduleFeatureDependenciesComponent.builder()
                .commonApi(commonApi())
                .build()
        return DaggerScheduleFeatureComponent.builder()
            .withDependencies(predictionFeatureDependencies)
            .exceptionHandlerDelegate(exceptionHandlerDelegate)
            .api(api)
            .router(router)
            .intent(intent)
            .build()

    }

}