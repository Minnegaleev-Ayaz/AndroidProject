package com.example.feature_predict_impl.presentation.ui.prediction.prediction.di

import androidx.fragment.app.Fragment
import com.example.feature_predict_impl.presentation.ui.prediction.prediction.PredictionFragment
import com.nefrit.common.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [PredictionModule::class,ViewModelModule::class]
)
interface PredictComponent {
    @Subcomponent.Factory
    interface Factory{
        fun create(
            @BindsInstance fragment: Fragment
        ): PredictComponent
    }
    fun inject(fragment: PredictionFragment)
}