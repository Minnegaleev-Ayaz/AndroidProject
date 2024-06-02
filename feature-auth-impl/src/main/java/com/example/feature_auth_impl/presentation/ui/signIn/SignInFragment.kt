package com.example.feature_auth_impl.presentation.ui.signIn

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.feature_auth_api.di.AuthFeatureApi
import com.example.feature_auth_impl.R
import com.example.feature_auth_impl.UsersAuthRouter
import com.example.feature_auth_impl.databinding.FragmentSignInBinding
import com.example.feature_auth_impl.di.AuthFeatureComponent
import com.example.feature_auth_impl.presentation.model.SignInForm
import com.example.feature_auth_impl.presentation.ui.signUp.SignUpViewModel
import com.nefrit.common.base.BaseFragment
import com.nefrit.common.di.FeatureUtils
import com.nefrit.common.utils.AsyncResult
import dagger.Lazy
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignInFragment : BaseFragment(R.layout.fragment_sign_in) {
    private val viewBinding: FragmentSignInBinding by viewBinding(FragmentSignInBinding::bind)
    @Inject
    lateinit var factory: Lazy<ViewModelProvider.Factory>
    private val viewModel: SignInViewModel by viewModels { factory.get() }

    override fun initViews() {
        lifecycleScope.launch {
            subscribe(viewModel)
        }
    }

    override fun inject() {
        FeatureUtils.getFeature<AuthFeatureComponent>(this, AuthFeatureApi::class.java)
            .signInComponentFactory().create(this).inject(this)
    }

    suspend fun subscribe(viewModel: SignInViewModel) {
        with(viewBinding) {
            signInBtn.setOnClickListener {
                viewModel.signIn(
                    email = emailEt.text.toString(),
                    password = passwordEt.text.toString()
                )
            }
            goToSignUpBtn.setOnClickListener {
                viewModel.openSignUp()
            }
        }
        viewModel.signInFlow.collect { result ->
            when (result) {
                is AsyncResult.Success -> {
                    Log.e("Ayaz", viewModel.preferencesImpl.getAutStatus().toString())
                    viewModel.saveUser(result.data.id)
                    viewModel.initializeUser()
                    Log.e("Ayaz", viewModel.preferencesImpl.getAutStatus().toString())
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