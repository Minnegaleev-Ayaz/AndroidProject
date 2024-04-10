package com.example.feature_auth_impl.domain.usecases

import com.example.feature_auth_impl.domain.mapper.UserUiModelMapper
import com.example.feature_auth_impl.domain.repository.UserRepository
import com.example.feature_auth_impl.presentation.model.SignUpForm
import com.example.feature_auth_impl.presentation.model.UserUiModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCreatedUserUseCase @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val repository: UserRepository,
    private val mapper:UserUiModelMapper
) {
    suspend operator fun invoke(signUpForm: SignUpForm): UserUiModel {
        return withContext(dispatcher) {
            val userData = repository.createUserWithEmailAndPassword(
                nickname = signUpForm.nickname,
                email = signUpForm.email,
                password = signUpForm.password,
                confPassword = signUpForm.confPassword
            )
            mapper.mapFromDataToUI(userData)

            /*mapper.mapDomainToUiModel(weatherData)*/
        }
    }
}