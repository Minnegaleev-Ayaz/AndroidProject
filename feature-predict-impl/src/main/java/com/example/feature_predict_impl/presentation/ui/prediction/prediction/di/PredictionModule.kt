package com.example.feature_predict_impl.presentation.ui.prediction.prediction.di

import androidx.lifecycle.ViewModel
import com.example.feature_predict_impl.presentation.ui.prediction.prediction.PredictionViewModel
import com.nefrit.common.di.viewmodel.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class PredictionModule {
    @Provides
    @[IntoMap ViewModelKey(PredictionViewModel::class)]
    fun provideWeatherInfoViewModel(viewModel: PredictionViewModel): ViewModel = viewModel
}