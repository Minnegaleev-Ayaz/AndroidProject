package com.nefrit.common.di

import android.content.Context
import com.nefrit.common.core.config.AppProperties
import com.nefrit.common.core.preferences.Preferences
import com.nefrit.common.core.resources.ResourceManager
import com.nefrit.common.notification.NotificationManagerWrapper
import com.nefrit.common.utils.DateFormatter
import kotlinx.coroutines.CoroutineDispatcher

interface CommonApi {

    fun applicationContext(): Context

    fun provideResourceManager(): ResourceManager

    fun provideAppProperties(): AppProperties
    fun provideCorutineDispatcher():CoroutineDispatcher
    fun providePreferences():Preferences
    fun provideDateFormatter():DateFormatter
    fun provideNotificationManagerWrapper():NotificationManagerWrapper
}