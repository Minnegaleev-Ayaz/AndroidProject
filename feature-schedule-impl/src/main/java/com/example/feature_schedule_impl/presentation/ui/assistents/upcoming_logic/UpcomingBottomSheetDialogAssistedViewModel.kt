package com.example.feature_schedule_impl.presentation.ui.assistents.upcoming_logic

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feature_schedule_impl.R
import com.example.feature_schedule_impl.ScheduleFeatureRouter
import com.example.feature_schedule_impl.presentation.model.upcoming.UpcomingMatchUiModel
import com.nefrit.common.base.BaseViewModel
import com.nefrit.common.core.resources.ResourceManager
import com.nefrit.common.notification.NotificationManagerWrapper
import com.nefrit.common.utils.DateFormatter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class UpcomingBottomSheetDialogAssistedViewModel @AssistedInject constructor(
    private val notificationManagerWrapper: NotificationManagerWrapper,
    private val intent: Intent,
    private val resourceManager: ResourceManager,
    private val router:ScheduleFeatureRouter,
    private val dateFormatter: DateFormatter,
    @Assisted(value = PARAM_KEY) private val model: UpcomingMatchUiModel
): BaseViewModel() {
    private var count:Int=1;
    fun predictButtonClick(){
        router.openPredFromUpcoming(model)
    }
    fun notificationButtonClick(context: Context, activity: FragmentActivity){
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS,
            ) == PackageManager.PERMISSION_DENIED
        ) {
            if (activity.shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                AlertDialog.Builder(context)
                    .setTitle(resourceManager.getString(R.string.notification))
                    .setMessage("Вы запретили приложению доступ к настройкам")
                    .setPositiveButton("назад") { dialog, which ->
                    }.setNegativeButton("Перейти в настройки"){
                            dialog,which ->
                        openApplicationSettings(activity)
                    }

                    .show()
            } else {
                AlertDialog.Builder(context)
                    .setTitle("Ошибка")
                    .setMessage("Вы запретили приложению доступ к настройкам")
                    .setPositiveButton(
                        "Перейти в настройки"
                    ) { dialog, which ->
                        openApplicationSettings(activity)

                    }
                    .show()
            }
        } else{
            notificationManagerWrapper.setNotificationAlarm(NotificationManagerWrapper.SimpleNotification(
                intent = intent,
                id = model.id!!,
                title = "Уведомление о матче",
                text = model.firstTeamName+" play with "+model.secondTeamName
            ),
                calculateDifference()
            )

        }
    }
    @AssistedFactory
    interface Factory {
        fun create(@Assisted(PARAM_KEY) param: UpcomingMatchUiModel): UpcomingBottomSheetDialogAssistedViewModel
    }
    private fun openApplicationSettings(activity: FragmentActivity) {
        val appSettingsIntent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.parse("package:" + activity.packageName)
        )
        startActivityForResult(activity,appSettingsIntent, NOTIFICATION_REQUEST_CODE,bundleOf())
    }
    private fun calculateDifference():Long{
        val formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy")

        val dateTime = LocalDateTime.parse(model.matchTime, formatter)

        val year = dateTime.year
        val month = dateTime.monthValue
        val day = dateTime.dayOfMonth
        val hour = dateTime.hour
        val minute = dateTime.minute

        val currentTime = LocalDateTime.now()
        val notificationtime = LocalDateTime.of(year,month,day,hour,minute)
        return Duration.between(currentTime,notificationtime).toMinutes()
    }
    companion object {
        private const val NOTIFICATION_REQUEST_CODE=12101
        const val PARAM_KEY = "ParamKey"

        @Suppress("UNCHECKED_CAST")
        fun provideFactory(
            assistedFactory: Factory,
            assistedParam: UpcomingMatchUiModel,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(assistedParam) as T
            }
        }
    }
}