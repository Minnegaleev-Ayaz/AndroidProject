package com.example.feature_splash_screen_impl.di

import android.content.Context
import com.nefrit.common.core.preferences.Preferences

interface SplashScreenFeatureDependencies {
    fun context():Context
    fun preferences(): Preferences

}