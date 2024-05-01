package com.nefrit.common.di.modules

import android.content.Context
import com.nefrit.common.core.preferences.Preferences
import com.nefrit.common.data.storage.PreferencesImpl
import com.nefrit.common.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class PreferencesModule {
    @Provides
    @ApplicationScope
    fun providePreferences(context: Context): Preferences {
        return PreferencesImpl(context)
    }
}