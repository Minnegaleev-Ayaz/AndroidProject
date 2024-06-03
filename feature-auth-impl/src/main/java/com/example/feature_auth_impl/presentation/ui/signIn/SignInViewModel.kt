package com.example.feature_auth_impl.presentation.ui.signIn

import androidx.lifecycle.viewModelScope
import com.example.feature_auth_impl.UsersAuthRouter
import com.example.feature_auth_impl.domain.usecases.SignInUseCase
import com.example.feature_auth_impl.presentation.model.SignInForm
import com.example.feature_auth_impl.presentation.model.SignUpForm
import com.example.feature_auth_impl.presentation.model.UserUiModel
import com.nefrit.common.base.BaseViewModel
import com.nefrit.common.data.storage.PreferencesImpl
import com.nefrit.common.utils.AsyncResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val router:UsersAuthRouter,
     val preferencesImpl: PreferencesImpl,
) : BaseViewModel() {
    private val _signInFlow = MutableStateFlow<AsyncResult<UserUiModel>?>(null)
    val signInFlow: StateFlow<AsyncResult<UserUiModel>?>
        get() = _signInFlow

    fun signIn(email:String,password:String) {
        viewModelScope.launch {
            _signInFlow.emit(AsyncResult.Loading)
            try {
                val result = signInUseCase.invoke(SignInForm(email,password))
                _signInFlow.emit(AsyncResult.Success(result))
            }catch (e:Throwable){
                _signInFlow.emit(AsyncResult.Error(e))
            }
        }
    }
    fun openSignUp(){
        router.openSignUpFromSignIn()
    }
    fun openPrediction(){
        router.openPredictionFromSignIn()
    }
    fun initializeUser(){
        preferencesImpl.saveAuthStatus(true)
    }
    fun saveUser(id:String){
        preferencesImpl.saveUserId(id)
    }
}