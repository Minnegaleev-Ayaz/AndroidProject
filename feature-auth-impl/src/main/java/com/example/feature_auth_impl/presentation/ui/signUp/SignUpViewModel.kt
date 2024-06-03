package com.example.feature_auth_impl.presentation.ui.signUp

import androidx.lifecycle.viewModelScope
import com.example.feature_auth_impl.UsersAuthRouter
import com.example.feature_auth_impl.domain.usecases.SignUpUseCase
import com.example.feature_auth_impl.presentation.model.SignInForm
import com.example.feature_auth_impl.presentation.model.SignUpForm
import com.example.feature_auth_impl.presentation.model.UserUiModel
import com.nefrit.common.base.BaseViewModel
import com.nefrit.common.data.storage.PreferencesImpl
import com.nefrit.common.utils.AsyncResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import java.net.PasswordAuthentication
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val router:UsersAuthRouter,
    private val preferencesImpl: PreferencesImpl,
) : BaseViewModel() {
    private val _signUpFlow = MutableStateFlow<AsyncResult<UserUiModel>?>(null)
    val signUpFlow: StateFlow<AsyncResult<UserUiModel>?>
        get() = _signUpFlow

    fun signUp(nickname: String, email: String, password: String, passwordConf: String) {
        viewModelScope.launch {
            _signUpFlow.emit(AsyncResult.Loading)
            try {
                val result = signUpUseCase.invoke(
                    SignUpForm(
                        nickname =nickname,
                        email =email,
                        password =password,
                        confPassword =passwordConf
                    )
                )
                _signUpFlow.emit(AsyncResult.Success(result))
            } catch (e: Throwable) {
                _signUpFlow.emit(AsyncResult.Error(e))
            }
        }
    }
    fun openSignIn(){
        router.openSignInFromSignUp()
    }
    fun openPrediction(){
        router.openPredictionFromSignUp()
    }
    fun initializeUser(){
        preferencesImpl.saveAuthStatus(true)
    }
    fun saveUser(id:String){
        preferencesImpl.saveUserId(id)
    }

}