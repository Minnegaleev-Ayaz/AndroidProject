package com.nefrit.app.di.deps

import com.example.feature_auth_api.di.AuthFeatureApi
import com.example.feature_auth_impl.di.AuthFeatureHolder
import com.example.feature_predict_api.di.PredictFeatureApi
import com.example.feature_predict_impl.di.PredictionFeatureHolder
import com.example.feature_profile_api.di.ProfileFeatureApi
import com.example.feature_profile_impl.di.ProfileFeatureHolder
import com.example.feature_schedule_api.di.ScheduleFeatureApi
import com.example.feature_schedule_impl.di.ScheduleFeatureHolder
import com.example.feature_search_api.di.SearchFeatureApi
import com.example.feature_search_impl.di.SearchFeatureHolder
import com.example.feature_splash_screen_api.di.SplashScreenApi
import com.example.feature_splash_screen_impl.di.SplashScreenFeatureHolder
import com.nefrit.app.App
import com.nefrit.common.di.FeatureApiHolder
import com.nefrit.common.di.FeatureContainer
import com.nefrit.common.di.scope.ApplicationScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
interface ComponentHolderModule {

    @ApplicationScope
    @Binds
    fun provideFeatureContainer(application: App): FeatureContainer

    @ApplicationScope
    @Binds
    @ClassKey(AuthFeatureApi::class)
    @IntoMap
    fun provideUserFeatureHolder(userFeatureHolder: AuthFeatureHolder): FeatureApiHolder
    @ApplicationScope
    @Binds
    @ClassKey(PredictFeatureApi::class)
    @IntoMap
    fun providePredictionFeatureHolder(predictionFeatureHolder: PredictionFeatureHolder): FeatureApiHolder
    @ApplicationScope
    @Binds
    @ClassKey(SplashScreenApi::class)
    @IntoMap
    fun provideSplashScreenFeatureHolder(splashScreenFeatureHolder: SplashScreenFeatureHolder):FeatureApiHolder
    @ApplicationScope
    @Binds
    @ClassKey(ScheduleFeatureApi::class)
    @IntoMap
    fun provideScheduleFeatureHolder(scheduleFeatureHolder: ScheduleFeatureHolder): FeatureApiHolder
    @ApplicationScope
    @Binds
    @ClassKey(SearchFeatureApi::class)
    @IntoMap
    fun provideSearchFeatureHolder(searchFeatureHolder: SearchFeatureHolder): FeatureApiHolder
    @ApplicationScope
    @Binds
    @ClassKey(ProfileFeatureApi::class)
    @IntoMap
    fun provideProfileFeatureHolder(profileFeatureHolder: ProfileFeatureHolder): FeatureApiHolder
}