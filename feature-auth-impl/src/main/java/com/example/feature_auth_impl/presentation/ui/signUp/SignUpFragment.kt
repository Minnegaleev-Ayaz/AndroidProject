package com.example.feature_auth_impl.presentation.ui.signUp

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.feature_auth_api.di.AuthFeatureApi
import com.example.feature_auth_impl.R
import com.example.feature_auth_impl.UsersAuthRouter
import com.example.feature_auth_impl.databinding.FragmentSignUpBinding
import com.example.feature_auth_impl.di.AuthFeatureComponent
import com.example.feature_auth_impl.presentation.model.SignUpForm
import com.nefrit.common.base.BaseFragment
import com.nefrit.common.di.FeatureUtils
import com.nefrit.common.utils.AsyncResult
import dagger.Lazy
import kotlinx.coroutines.launch
import javax.inject.Inject


class SignUpFragment : BaseFragment(R.layout.fragment_sign_up) {

    private val viewBinding: FragmentSignUpBinding by viewBinding(FragmentSignUpBinding::bind)

    @Inject
    lateinit var factory: Lazy<ViewModelProvider.Factory>


    private val viewModel: SignUpViewModel by viewModels { factory.get() }

    override fun initViews() {
        lifecycleScope.launch{
            subscribe(viewModel)
        }
    }


    override fun inject() {
        FeatureUtils.getFeature<AuthFeatureComponent>(this, AuthFeatureApi::class.java)
            .signUpComponentFactory().create(this).inject(this)
    }

    suspend fun subscribe(viewModel: SignUpViewModel) {
        with(viewBinding) {
            signInBtn.setOnClickListener {
                viewModel.signUp(
                    nickname = nicknameEt.text.toString(),
                    email = emailEt.text.toString(),
                    password = passwordEt.text.toString(),
                    passwordConf = passwordConfiramtionEt.text.toString()
                )
            }
            goToSignInBtn.setOnClickListener {
                viewModel.openSignIn()
            }
        }
        viewModel.signUpFlow.collect { result ->
            when (result) {
                is AsyncResult.Success -> {
                    viewModel.saveUser(result.data.id)
                    viewModel.initializeUser()
                    viewModel.openPrediction()
                }

                is AsyncResult.Error -> {
                    viewModel.errorHandlling(result.getExceptionOrNull()!!)
                }
                else -> {
                }
            }
        }
        viewModel.errorFlow.collect{
            it?.message?.let { it1 -> showToast(it1) }
        }
    }
}