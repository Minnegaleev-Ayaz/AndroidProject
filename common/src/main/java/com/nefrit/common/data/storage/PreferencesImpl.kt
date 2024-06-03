package com.nefrit.common.data.storage

import android.content.Context
import android.content.SharedPreferences
import com.nefrit.common.core.preferences.Preferences
import javax.inject.Inject

class PreferencesImpl @Inject constructor(context: Context) : Preferences {

    companion object {
        private const val KEY_AUTH_STATUS = "auth_status"
        private const val USER_ID_STATUS = "user_id_status"
        private const val WARNING_STATUS = "warning_status"
    }

    val prefs: SharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE)



    override fun saveAuthStatus(flag:Boolean) {
        prefs.edit().putBoolean(KEY_AUTH_STATUS, flag).apply()
    }

    override fun getAutStatus(): Boolean {
        return prefs.getBoolean(KEY_AUTH_STATUS,false)
    }

    override fun saveUserId(id: String) {
        prefs.edit().putString(USER_ID_STATUS,id).apply()
    }

    override fun getUserId(): String {
        return prefs.getString(USER_ID_STATUS,"").toString()
    }

    override fun getWarningStatus(): Boolean {
        return  prefs.getBoolean(WARNING_STATUS,false)
    }

    override fun setWarningStatus(flag: Boolean) {
        prefs.edit().putBoolean(WARNING_STATUS,flag).apply()
    }
}