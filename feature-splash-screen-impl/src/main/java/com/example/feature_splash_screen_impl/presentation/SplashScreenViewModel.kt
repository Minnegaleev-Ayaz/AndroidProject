package com.example.feature_splash_screen_impl.presentation

import androidx.lifecycle.viewModelScope
import com.example.feature_splash_screen_impl.SplashScreenRouter
import com.nefrit.common.base.BaseViewModel
import com.nefrit.common.data.storage.PreferencesImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SplashScreenViewModel(
    private val preferencesImpl: PreferencesImpl,
    private val router:SplashScreenRouter
):BaseViewModel() {
    private val _prefFlow = MutableStateFlow<Boolean>(false)
    val prefFlow :StateFlow<Boolean> get() = _prefFlow
    fun checkAuthStatus(){
        viewModelScope.launch {
            _prefFlow.emit(preferencesImpl.getAutStatus())

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