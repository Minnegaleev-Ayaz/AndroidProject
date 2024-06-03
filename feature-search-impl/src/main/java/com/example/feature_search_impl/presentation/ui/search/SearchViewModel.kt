package com.example.feature_search_impl.presentation.ui.search

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.feature_search_impl.R
import com.example.feature_search_impl.SearchFeatureRouter
import com.example.feature_search_impl.domain.mapper.MapFromDomainToPresentation
import com.example.feature_search_impl.domain.usecase.DeleteQuerryUseCase
import com.example.feature_search_impl.domain.usecase.GetPlayersUseCase
import com.example.feature_search_impl.domain.usecase.GetQuerryUseCase
import com.example.feature_search_impl.domain.usecase.GetTeamsUseCase
import com.example.feature_search_impl.domain.usecase.PutQuerryUseCase
import com.example.feature_search_impl.presentation.model.Heading
import com.example.feature_search_impl.presentation.model.player.PlayerUiModel
import com.example.feature_search_impl.presentation.model.querry.QuerryUiModel
import com.example.feature_search_impl.presentation.model.team.PlayerUi
import com.example.feature_search_impl.presentation.model.team.TeamUiModel
import com.example.panda_score_api.remote.ExceptionHandlerDelegate
import com.example.panda_score_api.remote.runCatching
import com.nefrit.common.base.BaseViewModel
import com.nefrit.common.core.resources.ResourceManager
import com.nefrit.common.utils.AsyncResult
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val getQuerryUseCase: GetQuerryUseCase,
    private val saveQuerryUseCase: PutQuerryUseCase,
    private val playersUseCase: GetPlayersUseCase,
    private val teamsUseCase: GetTeamsUseCase,
    private val router: SearchFeatureRouter,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
    private val mapper: MapFromDomainToPresentation,
    private val deleteQuerryUseCase: DeleteQuerryUseCase
) : BaseViewModel() {
    private val _getQuerryMatchesFlow =
        MutableSharedFlow<AsyncResult<List<QuerryUiModel>>?>()
    val getQuerryMatchesFlow: SharedFlow<AsyncResult<List<QuerryUiModel>>?>
        get() = _getQuerryMatchesFlow

    private val _saveQuerryMatchesFlow =
        MutableStateFlow<AsyncResult<QuerryUiModel>?>(AsyncResult.Loading)
    val saveQuerryMatchesFlow: StateFlow<AsyncResult<QuerryUiModel>?>
        get() = _saveQuerryMatchesFlow
    val _searchFlow = MutableStateFlow<AsyncResult<List<Any>>?>(AsyncResult.Loading)
    val searchFlow: StateFlow<AsyncResult<List<Any>>?> get() = _searchFlow


    fun getQuerries() {
        viewModelScope.launch {
            try {
                val result =
                    getQuerryUseCase.invoke().map { it -> mapper.mapQuerryFromDomainToUi(it) }
                _getQuerryMatchesFlow.emit(AsyncResult.Success(result))
            } catch (e: Exception) {
                _getQuerryMatchesFlow.emit(AsyncResult.Error(e))
            }
        }
    }

    fun searching(querry_text: String) {
        viewModelScope.launch {
            var resultList = mutableListOf<Any>()
            var playerList = mutableListOf<Any>()
            var teamList = mutableListOf<Any>()
            runCatching(exceptionHandlerDelegate) {
                playersUseCase.invoke(querry_text).map { mapper.mapPlayerFromDomainToUi(it) }
            }.onSuccess { result ->
                playerList = result.toMutableList()

            }.onFailure {
                _errorFlow.emit(it)
            }
            runCatching(exceptionHandlerDelegate) {
                teamsUseCase.invoke(querry_text).map { mapper.mapTeamFromDomainToUi(it) }
            }.onSuccess { result ->
                teamList = result.toMutableList()

            }.onFailure {
                _errorFlow.emit(it)
            }
            if ((teamList.size + playerList.size) == 0) {
                resultList.add(Heading(R.string.res_empty))
            }
            if (playerList.size >= 1) {
                resultList.add(Heading(R.string.players))
                resultList.addAll(playerList)
            }
            if (teamList.size >= 1) {
                resultList.add(Heading(R.string.teams))
                resultList.addAll(teamList)
            }
            _searchFlow.emit(AsyncResult.Success(resultList))
        }
    }

    fun saveQuerry(querry_text: String) {
        viewModelScope.launch {
            try {
                val result = mapper.mapQuerryFromDomainToUi(saveQuerryUseCase.invoke(querry_text))
                _saveQuerryMatchesFlow.emit(AsyncResult.Success(result))
            } catch (e: Exception) {
                _saveQuerryMatchesFlow.emit(AsyncResult.Error(e))
            }
        }
    }

    fun deleteQuerry(querryUiModel: QuerryUiModel) {
        viewModelScope.launch {
            deleteQuerryUseCase.invoke(querryUiModel)
        }
    }
    fun refresh(){
        viewModelScope.launch {
            _searchFlow.emit(null)
        }
    }
    fun openPlayer(model: PlayerUiModel){
        router.openPlayerFromSearch(model)
    }
    fun openTeam(model: TeamUiModel){
        router.openTeamFromSearch(model)
    }
}