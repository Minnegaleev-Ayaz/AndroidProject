package com.example.feature_schedule_impl.presentation.ui.assistents.upcoming_logic.di

import androidx.fragment.app.Fragment
import com.example.feature_schedule_impl.presentation.ui.assistents.streams.StreamsBottomSheetDialogFragment
import com.example.feature_schedule_impl.presentation.ui.assistents.streams.di.StreamsComponent
import com.example.feature_schedule_impl.presentation.ui.assistents.streams.di.StreamsModule
import com.example.feature_schedule_impl.presentation.ui.assistents.upcoming_logic.UpcomingBottomSheetDialogFragment
import com.nefrit.common.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [UpcomingBottomModule::class, ViewModelModule::class]
)
interface UpcomingBottomComponent {
    @Subcomponent.Factory
    interface Factory{
        fun create(
            @BindsInstance fragment: Fragment
        ): UpcomingBottomComponent
    }
    fun inject(fragment: UpcomingBottomSheetDialogFragment)
}