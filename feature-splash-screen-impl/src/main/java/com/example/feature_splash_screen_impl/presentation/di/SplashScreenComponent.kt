package com.example.feature_splash_screen_impl.presentation.di

import androidx.fragment.app.Fragment
import com.example.feature_splash_screen_impl.presentation.SplashScreenFragment
import com.nefrit.common.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [SplashScreenModule::class, ViewModelModule::class]
)
interface SplashScreenComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance fragment: Fragment
        ): SplashScreenComponent
    }

    fun inject(fragment: SplashScreenFragment)
}