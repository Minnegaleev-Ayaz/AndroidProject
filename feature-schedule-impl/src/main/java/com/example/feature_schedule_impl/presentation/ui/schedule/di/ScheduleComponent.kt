package com.example.feature_predict_impl.presentation.ui.prediction.prediction.di

import androidx.fragment.app.Fragment
import com.example.feature_schedule_impl.presentation.ui.schedule.ScheduleFragment
import com.nefrit.common.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [ScheduleModule::class,ViewModelModule::class]
)
interface ScheduleComponent {
    @Subcomponent.Factory
    interface Factory{
        fun create(
            @BindsInstance fragment: Fragment
        ): ScheduleComponent
    }
    fun inject(fragment: ScheduleFragment)
}