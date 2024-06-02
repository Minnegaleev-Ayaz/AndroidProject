package com.example.feature_schedule_impl.di

import com.nefrit.common.core.resources.ResourceManager
import com.nefrit.common.notification.NotificationManagerWrapper
import com.nefrit.common.utils.DateFormatter
import kotlinx.coroutines.CoroutineDispatcher

interface ScheduleFeatureDependencies {
    fun resourceManager(): ResourceManager

    fun corutineDispatcher(): CoroutineDispatcher
    fun notificationManagerWrapper():NotificationManagerWrapper
    fun dateFormatter():DateFormatter
}