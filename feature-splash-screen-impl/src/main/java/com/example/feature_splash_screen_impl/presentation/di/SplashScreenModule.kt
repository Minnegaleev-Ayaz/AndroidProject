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
    includes = [
        ViewModelModule::class
    ]
)
class SplashScreenModule {
    @Provides
    fun provideMainViewModel(fragment: Fragment, factory: ViewModelProvider.Factory): SplashScreenViewModel {
        return ViewModelProvider(fragment, factory)[SplashScreenViewModel::class.java]
    }

    @Provides
    @IntoMap
    @ViewModelKey(SplashScreenViewModel::class)
    fun provideRegisterViewModel(preferencesImpl: PreferencesImpl,router: SplashScreenRouter): ViewModel {
        return SplashScreenViewModel(preferencesImpl,router)
    }
}