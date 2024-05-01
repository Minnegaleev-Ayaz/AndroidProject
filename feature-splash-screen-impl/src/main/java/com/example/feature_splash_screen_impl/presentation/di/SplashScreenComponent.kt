package com.example.feature_splash_screen_impl.presentation.di

import androidx.fragment.app.Fragment
import com.example.feature_splash_screen_impl.presentation.SplashScreenFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [SplashScreenModule::class]
)
interface SplashScreenComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance fragment: Fragment
        ):SplashScreenComponent
    }
    fun inject(fragment: SplashScreenFragment)
}