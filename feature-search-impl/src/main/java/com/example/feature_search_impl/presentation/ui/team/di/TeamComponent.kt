package com.example.feature_search_impl.presentation.ui.team.di

import androidx.fragment.app.Fragment
import com.example.feature_search_impl.presentation.ui.team.TeamFragment
import com.nefrit.common.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [TeamModule::class, ViewModelModule::class]
)
interface TeamComponent {
    @Subcomponent.Factory
    interface Factory{
        fun create(
            @BindsInstance fragment: Fragment
        ): TeamComponent
    }
    fun inject(fragment: TeamFragment)
}