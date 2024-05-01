package com.example.feature_splash_screen_impl.presentation

import android.util.Log
import com.example.feature_splash_screen_api.di.SplashScreenApi
import com.example.feature_splash_screen_impl.R
import com.example.feature_splash_screen_impl.di.SplashScreenFeatureComponent
import com.nefrit.common.base.BaseFragment
import com.nefrit.common.di.FeatureUtils
import com.nefrit.common.utils.AsyncResult
import kotlinx.coroutines.delay

class SplashScreenFragment:BaseFragment<SplashScreenViewModel>(R.layout.fragment_splash_screen) {
    override fun initViews() {
        viewModel.checkAuthStatus()
    }

    override fun inject() {
        FeatureUtils.getFeature<SplashScreenFeatureComponent>(this, SplashScreenApi::class.java)
            .splashScreenFactory().create(this).inject(this)
    }
    override suspend fun subscribe(viewModel: SplashScreenViewModel) {
        viewModel.prefFlow.collect { result ->
            delay(2000)
            Log.e("Ayaz", result.toString()+" - splash")
            if (result){
                viewModel.openPredictions()
                viewModel.setAuthStatus(false)
                viewModel.setAuthStatus(true)
            }else{
                viewModel.openSignUp()
            }
        }
    }

}