package com.nefrit.app.di.app

import android.content.Context
import android.content.Intent
import com.nefrit.app.App
import com.nefrit.app.di.main.MainActivity
import com.nefrit.common.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @ApplicationScope
    @Provides
    fun provideContext(application: App): Context {

        return application
    }
    @ApplicationScope
    @Provides
    fun provideIntent(context: Context): Intent {
        return Intent(context, MainActivity::class.java)
    }
}