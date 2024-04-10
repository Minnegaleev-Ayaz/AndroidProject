package com.nefrit.app.di.app

import com.example.feature_auth_impl.UsersAuthRouter
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
    fun provideMainRouter(navigator: Navigator): UsersAuthRouter = navigator
}