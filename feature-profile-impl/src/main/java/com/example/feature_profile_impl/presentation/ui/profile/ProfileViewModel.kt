package com.example.feature_profile_impl.presentation.ui.profile

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.feature_profile_api.domain.repository.model.DataUser
import com.example.feature_profile_impl.ProfileFeatureRouter
import com.example.feature_profile_impl.domain.usecase.GetUserUseCase

import com.nefrit.common.base.BaseViewModel
import com.nefrit.common.data.storage.PreferencesImpl
import com.nefrit.common.utils.AsyncResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val preferencesImpl: PreferencesImpl,
    private val useCase: GetUserUseCase,
    private val router:ProfileFeatureRouter
):BaseViewModel() {
    private val _userFlow =
        MutableStateFlow<AsyncResult<DataUser>?>(AsyncResult.Loading)
    val userFlow: StateFlow<AsyncResult<DataUser>?>
        get() = _userFlow
    fun subscribe(){
        viewModelScope.launch {
            try{
                 useCase.invoke(_userFlow)
            }catch (e:Exception){
                AsyncResult.Error(e)
            }
        }
    }
    fun logOutFromAccount(){
        preferencesImpl.saveAuthStatus(false)
        preferencesImpl.saveUserId("")
        router.openSignInFromProfile()
    }
}