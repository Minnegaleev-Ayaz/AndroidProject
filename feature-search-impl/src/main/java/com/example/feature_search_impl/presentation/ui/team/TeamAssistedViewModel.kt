package com.example.feature_search_impl.presentation.ui.team

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feature_search_impl.SearchFeatureRouter
import com.example.feature_search_impl.presentation.model.team.PlayerUi
import com.example.feature_search_impl.presentation.model.team.TeamUiModel
import com.nefrit.common.base.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class TeamAssistedViewModel @AssistedInject constructor(
    private val router:SearchFeatureRouter,
    @Assisted(value = PARAM_KEY) private val model: TeamUiModel
) : BaseViewModel() {
    fun openPlayer(model: PlayerUi){
        router.openPlayerFromTeam(model)
    }
    @AssistedFactory
    interface Factory {
        fun create(@Assisted(PARAM_KEY) param: TeamUiModel): TeamAssistedViewModel
    }

    companion object {
        const val PARAM_KEY = "ParamKey"

        @Suppress("UNCHECKED_CAST")
        fun provideFactory(
            assistedFactory: Factory,
            assistedParam: TeamUiModel,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(assistedParam) as T
            }
        }
    }
}