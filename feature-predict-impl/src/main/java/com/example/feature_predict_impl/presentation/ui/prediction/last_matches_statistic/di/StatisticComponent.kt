package com.example.feature_predict_impl.presentation.ui.prediction.last_matches_statistic.di

import androidx.fragment.app.Fragment
import com.example.feature_predict_impl.presentation.ui.prediction.last_matches_statistic.StatisticFragment
import com.example.feature_predict_impl.presentation.ui.prediction.prediction.PredictionFragment
import com.example.feature_predict_impl.presentation.ui.prediction.prediction.di.PredictionModule
import com.nefrit.common.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent

@Subcomponent(
    modules = [StatisticModule::class, ViewModelModule::class]
)
interface StatisticComponent {
    @Subcomponent.Factory
    interface Factory{
        fun create(
            @BindsInstance fragment: Fragment
        ): StatisticComponent
    }
    fun inject(fragment: StatisticFragment)
}