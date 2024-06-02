package com.example.feature_schedule_impl.di


import android.content.Intent
import com.example.feature_predict_impl.presentation.ui.prediction.prediction.di.ScheduleComponent
import com.example.feature_schedule_api.di.ScheduleFeatureApi
import com.example.feature_schedule_impl.ScheduleFeatureRouter
import com.example.feature_schedule_impl.presentation.ui.assistents.streams.di.StreamsComponent
import com.example.feature_schedule_impl.presentation.ui.assistents.upcoming_logic.di.UpcomingBottomComponent
import com.example.panda_score_api.remote.ExceptionHandlerDelegate
import com.example.panda_score_api.remote.PandasScoreApi
import com.nefrit.common.di.CommonApi
import com.nefrit.common.di.scope.FeatureScope
import dagger.BindsInstance
import dagger.Component

@FeatureScope
@Component(
    modules = [ScheduleFeatureModule::class],
    dependencies = [ScheduleFeatureDependencies::class]
)
interface ScheduleFeatureComponent : ScheduleFeatureApi {
    fun scheduleComponentFactory(): ScheduleComponent.Factory
    fun streamComponentFactory():StreamsComponent.Factory
    fun upcomingBottomComponentFactory():UpcomingBottomComponent.Factory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun api(pandasScoreApi: PandasScoreApi): Builder

        @BindsInstance
        fun exceptionHandlerDelegate(exceptionHandlerDelegate: ExceptionHandlerDelegate): Builder

        @BindsInstance
        fun router(predictionFeatureRouter: ScheduleFeatureRouter): Builder
        @BindsInstance
        fun intent(intent: Intent):Builder
        fun withDependencies(deps: ScheduleFeatureDependencies): Builder
        fun build(): ScheduleFeatureComponent
    }

    @Component(
        dependencies = [
            CommonApi::class
        ]
    )
    interface ScheduleFeatureDependenciesComponent : ScheduleFeatureDependencies
}