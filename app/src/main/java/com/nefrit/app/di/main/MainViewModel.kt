package com.nefrit.app.di.main

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.nefrit.common.base.BaseViewModel
import com.nefrit.common.data.storage.PreferencesImpl
import com.nefrit.common.utils.AsyncResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val preferencesImpl: PreferencesImpl
) : BaseViewModel() {
    private val preferenceChangeListener =
        SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
            updateBottomNavigationView()
        }
    init {
        preferencesImpl.prefs.registerOnSharedPreferenceChangeListener(preferenceChangeListener)
    }
    private val _sharedPreferencesFlow = MutableStateFlow<AsyncResult<Boolean>?>(null)
    val sharedPreferencesFlow: MutableStateFlow<AsyncResult<Boolean>?>
        get() = _sharedPreferencesFlow

    fun updateBottomNavigationView() {
        viewModelScope.launch {
            try {
                val authStatus = preferencesImpl.getAutStatus()
                Log.e("Ayaz",authStatus.toString())
                _sharedPreferencesFlow.emit(AsyncResult.Success(authStatus))
            } catch (e: Exception) {
                _sharedPreferencesFlow.emit(AsyncResult.Error(e))
            }
        }

    }
    fun getAuthValue():Boolean{
        return preferencesImpl.getAutStatus()
    }
}