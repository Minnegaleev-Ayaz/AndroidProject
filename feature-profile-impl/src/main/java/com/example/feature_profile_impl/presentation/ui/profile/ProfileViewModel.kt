package com.example.feature_profile_impl.presentation.ui.profile

import android.provider.ContactsContract.Profile
import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.viewbinding.ViewBinding
import com.example.feature_profile_impl.ProfileFeatureRouter
import com.example.feature_profile_impl.domain.usecase.GetUserUseCase
import com.google.firebase.auth.FirebaseUser
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
        MutableStateFlow<AsyncResult<FirebaseUser>?>(AsyncResult.Loading)
    val userFlow: StateFlow<AsyncResult<FirebaseUser>?>
        get() = _userFlow
    fun subscribe(){
        viewModelScope.launch {
            try{
                val result = useCase.invoke()
                _userFlow.emit(AsyncResult.Success(result!!))
                Log.e("Ayaz","FirebaseUser - "+result.toString())
            }catch (e:Exception){
                AsyncResult.Error(e)
                Log.e("Ayaz",e.toString())
            }
        }
    }
    fun logOutFromAccount(){
        Log.e("Ayaz",preferencesImpl.getUserId()+" - user id")
        preferencesImpl.saveAuthStatus(false)
        preferencesImpl.saveUserId("")
        router.openSignInFromProfile()
    }
}