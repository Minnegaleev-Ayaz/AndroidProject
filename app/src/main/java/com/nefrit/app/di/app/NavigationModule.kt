package com.nefrit.app.di.app

import com.example.feature_auth_impl.UsersAuthRouter
import com.example.feature_predict_impl.PredictionFeatureRouter
import com.example.feature_profile_impl.ProfileFeatureRouter
import com.example.feature_schedule_impl.ScheduleFeatureRouter
import com.example.feature_search_impl.SearchFeatureRouter
import com.example.feature_splash_screen_impl.SplashScreenRouter
import com.nefrit.app.navigation.Navigator
import com.nefrit.common.di.scope.ApplicationScope

import dagger.Module
import dagger.Provides

@Module
class NavigationModule {

    @ApplicationScope
    @Provides
    fun provideNavigator(): Navigator = Navigator()

    @ApplicationScope
    @Provides
    fun provideAuthRouter(navigator: Navigator): UsersAuthRouter = navigator
    @ApplicationScope
    @Provides
    fun providePredictRouter(navigator: Navigator): PredictionFeatureRouter = navigator
    @ApplicationScope
    @Provides
    fun provideSplashScreenRouter(navigator: Navigator): SplashScreenRouter = navigator
    @ApplicationScope
    @Provides
    fun provideScheduleRouter(navigator: Navigator): ScheduleFeatureRouter = navigator
    @ApplicationScope
    @Provides
    fun provideSearchRouter(navigator: Navigator): SearchFeatureRouter = navigator
    @ApplicationScope
    @Provides
    fun provideProfileRouter(navigator: Navigator): ProfileFeatureRouter = navigator
}