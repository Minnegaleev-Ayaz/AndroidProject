package com.example.feature_splash_screen_impl.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.feature_splash_screen_impl.SplashScreenRouter
import com.nefrit.common.base.BaseViewModel
import com.nefrit.common.data.storage.PreferencesImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor(
    private val preferencesImpl: PreferencesImpl,
    private val router:SplashScreenRouter
):BaseViewModel() {
    private val _prefFlow = MutableStateFlow<Boolean?>(null)
    val prefFlow :StateFlow<Boolean?> get() = _prefFlow
    fun checkAuthStatus(){
        Log.e("Ayaz",preferencesImpl.getAutStatus().toString()+" also status")
        viewModelScope.launch {
            _prefFlow.emit(preferencesImpl.getAutStatus()?:false)
        }
    }
    fun setAuthStatus(flag:Boolean){
        preferencesImpl.saveAuthStatus(flag)
    }
    fun openSignUp(){
        router.openSignUpFromSplashScreen()
    }
    fun openPredictions(){
        router.openPredictionFromSplashScreen()
    }
}