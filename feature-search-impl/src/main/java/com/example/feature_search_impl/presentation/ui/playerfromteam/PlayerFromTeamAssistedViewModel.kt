package com.example.feature_search_impl.presentation.ui.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feature_search_impl.presentation.model.player.PlayerUiModel
import com.example.feature_search_impl.presentation.model.team.PlayerUi
import com.nefrit.common.base.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class PlayerFromTeamAssistedViewModel @AssistedInject constructor(
    @Assisted(value = PARAM_KEY) private val model: PlayerUi
):BaseViewModel() {
    @AssistedFactory
    interface Factory {
        fun create(@Assisted(PARAM_KEY) param: PlayerUi): PlayerFromTeamAssistedViewModel
    }
    companion object {
        const val PARAM_KEY = "ParamKey"

        @Suppress("UNCHECKED_CAST")
        fun provideFactory(
            assistedFactory: Factory,
            assistedParam: PlayerUi,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(assistedParam) as T
            }
        }
    }
}