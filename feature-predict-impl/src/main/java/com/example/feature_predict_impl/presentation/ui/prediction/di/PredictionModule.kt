package com.example.feature_predict_impl.presentation.ui.prediction.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feature_predict_impl.PredictionFeatureRouter
import com.example.feature_predict_impl.data.ExceptionHandlerDelegate
import com.example.feature_predict_impl.domain.usecases.UpcomingMatchesUseCase
import com.example.feature_predict_impl.presentation.ui.prediction.PredictionViewModel
import com.nefrit.common.di.viewmodel.ViewModelKey
import com.nefrit.common.di.viewmodel.ViewModelModule
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(
    includes = [
        ViewModelModule::class
    ]
)
class PredictionModule {

    @Provides
    fun provideMainViewModel(fragment: Fragment, factory: ViewModelProvider.Factory): PredictionViewModel {
        return ViewModelProvider(fragment, factory)[PredictionViewModel::class.java]
    }

    @Provides
    @IntoMap
    @ViewModelKey(PredictionViewModel::class)
    fun provideRegisterViewModel(useCase: UpcomingMatchesUseCase,router: PredictionFeatureRouter,exceptionHandlerDelegate: ExceptionHandlerDelegate): ViewModel {
        return PredictionViewModel(useCase,router,exceptionHandlerDelegate)
    }
}