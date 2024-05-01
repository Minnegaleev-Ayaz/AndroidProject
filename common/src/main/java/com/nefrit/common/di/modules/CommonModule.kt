package com.nefrit.common.di.modules

import android.app.NotificationManager
import android.content.Context
import com.nefrit.common.core.config.AppProperties
import com.nefrit.common.core.preferences.Preferences
import com.nefrit.common.core.resources.ResourceManager
import com.nefrit.common.core.resources.ResourceManagerImpl
import com.nefrit.common.data.storage.PreferencesImpl
import com.nefrit.common.di.scope.ApplicationScope
import com.nefrit.common.di.scope.FeatureScope
import com.nefrit.common.notification.NotificationManagerWrapper
import com.nefrit.common.notification.NotificationManagerWrapperImpl
import com.nefrit.common.utils.DateFormatter
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class CommonModule {

    @Provides
    @ApplicationScope
    fun provideResourceManager(context: Context): ResourceManager {
        return ResourceManagerImpl(context)
    }

    @Provides
    @ApplicationScope
    fun provideAppProperties(context: Context): AppProperties {
        return AppProperties(context)
    }

    @Provides
    fun provideDateFormatter(): DateFormatter {
        return DateFormatter()
    }

    @Provides
    fun provideNotificationWrapper(
        context: Context,
        notificationManager: NotificationManager
    ): NotificationManagerWrapper {
        return NotificationManagerWrapperImpl(context, notificationManager)
    }

    @Provides
    fun provideCorutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

}