package com.example.feature_predict_impl.presentation.ui.prediction.bottom

import android.util.Log
import android.util.Pair
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.feature_predict_impl.domain.mapper.FromDomainToPresentationMapper
import com.example.feature_predict_impl.domain.usecases.ReadMyPredictUseCase
import com.example.feature_predict_impl.domain.usecases.ReadPredictsUseCase
import com.example.feature_predict_impl.domain.usecases.WritePredictsUseCase
import com.example.feature_predict_impl.presentation.model.MatchPresentationModel
import com.example.feature_predict_impl.presentation.model.PredictPresentationModel
import com.nefrit.common.base.BaseViewModel
import com.nefrit.common.core.resources.ResourceManager
import com.nefrit.common.utils.AsyncResult
import com.nefrit.common.utils.DateFormatter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class PredictionBottomSheetDialogAssistedViewModel @AssistedInject constructor(
    private val writeUseCase: WritePredictsUseCase,
    private val readPredictsUseCase: ReadPredictsUseCase,
    private val readMyPredictsUseCase: ReadMyPredictUseCase,
    private val resourceManager: ResourceManager,
    private val mapper: FromDomainToPresentationMapper,
    private val dateFormatter: DateFormatter,
    @Assisted(value = PARAM_KEY) private val model: MatchPresentationModel
): BaseViewModel() {
    private val _myPredictFlow =
        MutableStateFlow<AsyncResult<PredictPresentationModel>?>(AsyncResult.Loading)
    val myPredictFlow: StateFlow<AsyncResult<PredictPresentationModel>?>
        get() = _myPredictFlow

    private val _predictFlow =
        MutableStateFlow<AsyncResult<List<PredictPresentationModel>>?>(AsyncResult.Loading)
    val predictFlow: StateFlow<AsyncResult<List<PredictPresentationModel>>?>
        get() = _predictFlow
     fun initializeVoting(){
        viewModelScope.launch {
            _predictFlow.emit(AsyncResult.Loading)
            try {
                val result = readPredictsUseCase.invoke(model.id?.toLong()!!,_predictFlow)
            }catch (e:Throwable){
                _predictFlow.emit(AsyncResult.Error(e))
            }
        }
    }
    fun initializeMyVote(){
        viewModelScope.launch {
            _myPredictFlow.emit(AsyncResult.Loading)
            try {
                val result = readMyPredictsUseCase.invoke(model.id?.toLong()!!,_myPredictFlow)
            }catch (e:Throwable){
                _predictFlow.emit(AsyncResult.Error(e))
            }
        }
    }
    suspend fun voting(vote:Int){
        model.id?.let { writeUseCase.invoke(it.toString(),vote) }
    }
    fun calculatePercenatge(list:List<PredictPresentationModel>): Pair<String, String> {
        val cntFirst = list.filter { it.vote=="0" }.count()
        val cntSecond = list.filter { it.vote=="1" }.count()
        if (cntFirst==cntSecond) {
            return Pair("50", "50")
        }else{
            if (cntFirst==0){
                return Pair("0","100")
            }else{
                if (cntSecond==0){
                    return Pair("100","0")
                }else{
                    return Pair((cntFirst*100).div(list.size).toString(),(cntSecond*100).div(list.size).toString())
                }
            }
        }
    }
    @AssistedFactory
    interface Factory {
        fun create(@Assisted(PARAM_KEY) param: MatchPresentationModel): PredictionBottomSheetDialogAssistedViewModel
    }
    companion object {
        private const val NOTIFICATION_REQUEST_CODE=12101
        const val PARAM_KEY = "ParamKey"

        @Suppress("UNCHECKED_CAST")
        fun provideFactory(
            assistedFactory: Factory,
            assistedParam: MatchPresentationModel,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(assistedParam) as T
            }
        }
    }
}