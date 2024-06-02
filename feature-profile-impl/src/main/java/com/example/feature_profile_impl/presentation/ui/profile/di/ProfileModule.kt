package com.example.feature_profile_impl.presentation.ui.profile.di

import androidx.lifecycle.ViewModel
import com.example.feature_profile_impl.presentation.ui.profile.ProfileViewModel
import com.nefrit.common.di.viewmodel.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ProfileModule {
    @Provides
    @[IntoMap ViewModelKey(ProfileViewModel::class)]
    fun provideWeatherInfoViewModel(viewModel: ProfileViewModel): ViewModel = viewModel
}