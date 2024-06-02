package com.example.feature_auth_impl.presentation.ui.signIn.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feature_auth_impl.UsersAuthRouter
import com.example.feature_auth_impl.domain.usecases.SignInUseCase
import com.example.feature_auth_impl.domain.usecases.SignUpUseCase
import com.example.feature_auth_impl.presentation.ui.signIn.SignInViewModel
import com.example.feature_auth_impl.presentation.ui.signUp.SignUpViewModel
import com.nefrit.common.data.storage.PreferencesImpl
import com.nefrit.common.di.viewmodel.ViewModelKey
import com.nefrit.common.di.viewmodel.ViewModelModule
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module()
class SignInModule {
    @Provides
    @[IntoMap ViewModelKey(SignInViewModel::class)]
    fun provideWeatherInfoViewModel(viewModel:SignInViewModel): ViewModel = viewModel
}