package com.example.feature_schedule_impl.presentation.ui.assistents.streams.di

import androidx.fragment.app.Fragment
import com.example.feature_schedule_impl.presentation.ui.assistents.streams.StreamsBottomSheetDialogFragment
import com.example.feature_schedule_impl.presentation.ui.assistents.upcoming_logic.UpcomingBottomSheetDialogFragment
import com.example.feature_schedule_impl.presentation.ui.schedule.ScheduleFragment
import com.nefrit.common.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [StreamsModule::class, ViewModelModule::class]
)
interface StreamsComponent {
    @Subcomponent.Factory
    interface Factory{
        fun create(
            @BindsInstance fragment: Fragment
        ): StreamsComponent
    }
    fun inject(fragment: StreamsBottomSheetDialogFragment)
}