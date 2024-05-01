package com.example.feature_auth_impl.presentation.ui.signUp

import android.content.Context
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
import javax.inject.Inject


class SignUpFragment : BaseFragment<SignUpViewModel>(R.layout.fragment_sign_up) {

    private val viewBinding: FragmentSignUpBinding by viewBinding(FragmentSignUpBinding::bind)

    override fun initViews() {
    }

    override fun inject() {
        FeatureUtils.getFeature<AuthFeatureComponent>(this, AuthFeatureApi::class.java)
            .signUpComponentFactory().create(this).inject(this)
    }

    override suspend fun subscribe(viewModel: SignUpViewModel) {
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