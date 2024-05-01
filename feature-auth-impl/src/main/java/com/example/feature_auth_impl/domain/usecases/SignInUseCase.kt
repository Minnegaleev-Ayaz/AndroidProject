package com.example.feature_auth_impl.domain.usecases

import com.example.feature_auth_impl.domain.mapper.UserUiModelMapper
import com.example.feature_auth_impl.domain.repository.UserRepository
import com.example.feature_auth_impl.presentation.model.SignInForm
import com.example.feature_auth_impl.presentation.model.SignUpForm
import com.example.feature_auth_impl.presentation.model.UserUiModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val repository: UserRepository,
    private val mapper: UserUiModelMapper
) {
    suspend operator fun invoke(signInForm: SignInForm): UserUiModel {
        return withContext(Dispatchers.IO) {
            val userData = repository.signInwithEmailAndPassword(
                email = signInForm.email,
                password = signInForm.password,
            )
            mapper.mapFromDataToUI(userData)
        }
    }
}