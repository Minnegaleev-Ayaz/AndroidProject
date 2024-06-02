package com.example.feature_splash_screen_impl.presentation.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feature_splash_screen_impl.SplashScreenRouter
import com.example.feature_splash_screen_impl.presentation.SplashScreenViewModel
import com.nefrit.common.data.storage.PreferencesImpl
import com.nefrit.common.di.viewmodel.ViewModelKey
import com.nefrit.common.di.viewmodel.ViewModelModule
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(
)
class SplashScreenModule {
    @Provides
    @[IntoMap ViewModelKey(SplashScreenViewModel::class)]
    fun provideWeatherInfoViewModel(viewModel:SplashScreenViewModel): ViewModel = viewModel
}