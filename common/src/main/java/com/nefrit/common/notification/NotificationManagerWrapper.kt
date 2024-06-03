package com.nefrit.common.notification

import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Parcelable
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.nefrit.common.R
import kotlinx.android.parcel.Parcelize

interface NotificationManagerWrapper {
    fun setNotificationAlarm(simpleNotification: SimpleNotification,delta: Long)
    fun notify(simpleNotification: SimpleNotification, delta: Long)
    @Parcelize
    class SimpleNotification(
        val id: Int = 1,
        val channel: Channel = Channel.DEFAULT,
        val title: String? = null,
        val smallIcon: Int = R.mipmap.ic_launcher,
        val largeIcon: Int = R.mipmap.ic_launcher,
        val text: String? = null,
        val intent: Intent,
    ):Parcelable

    enum class Channel(val channelName: String, val channelDescription: String) {
        DEFAULT("Default", "Default channel");

        fun getId(context: Context) = "${context.packageName}_$channelName"
    }
}

class NotificationManagerWrapperImpl(
    private val context: Context,
    private val notificationManager: NotificationManager,
) : NotificationManagerWrapper {

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel() {
        val channels = NotificationManagerWrapper.Channel.values().map {
            NotificationChannel(it.getId(context), it.channelName, NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = it.channelDescription
            }
        }

        notificationManager.createNotificationChannels(channels)
    }

    override fun setNotificationAlarm(
        simpleNotification: NotificationManagerWrapper.SimpleNotification,
        delta: Long
    ) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, NotificationReceiver::class.java)
        intent.putExtra("notification", simpleNotification)
        intent.putExtra("delta", delta)
        val pendingIntent = PendingIntent.getBroadcast(context, simpleNotification.id, intent, PendingIntent.FLAG_IMMUTABLE)

        // Установите будильник на время, равное текущему времени плюс задержку
        val triggerTime = System.currentTimeMillis() + delta*60000
        alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
    }

    override fun notify(
        simpleNotification: NotificationManagerWrapper.SimpleNotification,
        delta: Long
    ) {
        val notification = mapSimpleNotification(simpleNotification)
        notificationManager.notify(simpleNotification.id, notification)
    }

    private fun mapSimpleNotification(simpleNotification: NotificationManagerWrapper.SimpleNotification): Notification {
        val style = NotificationCompat.BigTextStyle().bigText(simpleNotification.text)
        val pendingIntent = PendingIntent.getActivity(context, simpleNotification.id, simpleNotification.intent, PendingIntent.FLAG_IMMUTABLE)

        return NotificationCompat.Builder(context, simpleNotification.channel.getId(context))
            .setStyle(style)
            .setSmallIcon(simpleNotification.smallIcon)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, simpleNotification.largeIcon))
            .setContentTitle(simpleNotification.title)
            .setContentText(simpleNotification.text)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
    }
}

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // Получите данные уведомления из интента
        val simpleNotification = intent.getParcelableExtra<NotificationManagerWrapper.SimpleNotification>("notification")!!
        val delta = intent.getLongExtra("delta", 0)
        // Создайте экземпляр NotificationManagerWrapperImpl и отправьте уведомление
        val notificationManager = NotificationManagerWrapperImpl(context, context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
        notificationManager.notify(simpleNotification, delta)
    }
}