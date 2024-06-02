package com.example.feature_schedule_impl.presentation.ui.assistents.streams

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feature_schedule_impl.presentation.model.running.RunningMatchUiModel
import com.nefrit.common.base.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class StreamsBottomSheetDialogAssistedViewModel @AssistedInject constructor(
    @Assisted(value = PARAM_KEY) private val model: RunningMatchUiModel
):BaseViewModel() {
    @AssistedFactory
    interface Factory {
        fun create(@Assisted(PARAM_KEY) param: RunningMatchUiModel): StreamsBottomSheetDialogAssistedViewModel
    }

    companion object {
        const val PARAM_KEY = "ParamKey"

        @Suppress("UNCHECKED_CAST")
        fun provideFactory(
            assistedFactory: Factory,
            assistedParam: RunningMatchUiModel,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(assistedParam) as T
            }
        }
    }
}