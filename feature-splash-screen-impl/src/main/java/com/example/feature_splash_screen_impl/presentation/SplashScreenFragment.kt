package com.example.feature_splash_screen_impl.presentation

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.feature_splash_screen_api.di.SplashScreenApi
import com.example.feature_splash_screen_impl.R
import com.example.feature_splash_screen_impl.di.SplashScreenFeatureComponent
import com.nefrit.common.base.BaseFragment
import com.nefrit.common.di.FeatureUtils
import com.nefrit.common.utils.AsyncResult
import dagger.Lazy
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashScreenFragment:BaseFragment(R.layout.fragment_splash_screen) {
    @Inject
    lateinit var factory: Lazy<ViewModelProvider.Factory>
    private val viewModel: SplashScreenViewModel by viewModels { factory.get() }
    override fun initViews() {
        viewModel.checkAuthStatus()
        lifecycleScope.launch {
            subscribe(viewModel)
        }
    }

    override fun inject() {
        FeatureUtils.getFeature<SplashScreenFeatureComponent>(this, SplashScreenApi::class.java)
            .splashScreenFactory().create(this).inject(this)
    }
     suspend fun subscribe(viewModel: SplashScreenViewModel) {
        viewModel.prefFlow.collect { result ->
            if (result == true){
                viewModel.openPredictions()
                viewModel.setAuthStatus(false)
                viewModel.setAuthStatus(true)
            }else{
                viewModel.openSignUp()
            }
        }
    }

}