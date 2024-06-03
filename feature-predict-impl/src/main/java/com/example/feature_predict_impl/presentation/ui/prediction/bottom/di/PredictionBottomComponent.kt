package com.example.feature_predict_impl.presentation.ui.prediction.bottom.di

import androidx.fragment.app.Fragment
import com.example.feature_predict_impl.presentation.ui.prediction.bottom.PredictionBottomSheetDialogFragment
import com.nefrit.common.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [PredictionBottomModule::class, ViewModelModule::class]
)
interface PredictionBottomComponent {
    @Subcomponent.Factory
    interface Factory{
        fun create(
            @BindsInstance fragment: Fragment
        ): PredictionBottomComponent
    }
    fun inject(fragment: PredictionBottomSheetDialogFragment)
}