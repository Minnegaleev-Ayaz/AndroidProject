package com.example.feature_profile_impl.presentation.ui.profile.di

import androidx.fragment.app.Fragment
import com.example.feature_profile_impl.presentation.ui.profile.ProfileFragment
import com.nefrit.common.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [ProfileModule::class, ViewModelModule::class]
)
interface ProfileComponent {
    @Subcomponent.Factory
    interface Factory{
        fun create(
            @BindsInstance fragment: Fragment
        ): ProfileComponent
    }
    fun inject(fragment: ProfileFragment)
}