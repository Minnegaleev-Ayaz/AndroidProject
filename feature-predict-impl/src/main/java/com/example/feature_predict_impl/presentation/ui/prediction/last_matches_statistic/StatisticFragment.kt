package com.example.feature_predict_impl.presentation.ui.prediction.last_matches_statistic

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.feature_predict_api.di.PredictFeatureApi
import com.example.feature_predict_impl.R
import com.example.feature_predict_impl.databinding.FragmentStatisticBinding
import com.example.feature_predict_impl.di.PredictionFeatureComponent
import com.nefrit.common.base.BaseFragment
import com.nefrit.common.di.FeatureUtils
import com.nefrit.common.utils.AsyncResult
import com.nefrit.common.utils.ParamsKey
import kotlinx.coroutines.launch
import javax.inject.Inject

class StatisticFragment : BaseFragment(R.layout.fragment_statistic) {
    private val viewBinding:FragmentStatisticBinding by viewBinding(FragmentStatisticBinding::bind)
    @Inject
    lateinit var vmFactory: StatisticAssistedViewModel.Factory

    private val viewModel: StatisticAssistedViewModel by viewModels {
        StatisticAssistedViewModel.provideFactory(vmFactory, assistedParam = arguments?.getInt(ParamsKey.TEAM_ID)!!.toInt())
    }

    override fun initViews() {
        viewModel.initialize()
        lifecycleScope.launch{
            subscribe(viewModel)
        }
    }

    override fun inject() {
        FeatureUtils.getFeature<PredictionFeatureComponent>(this,PredictFeatureApi::class.java)
            .statisticComponentFactory().create(this).inject(this)
    }
    suspend fun subscribe(viewModel:StatisticAssistedViewModel){
        viewModel.pastMatchesFlow.collect{result->
            with(viewBinding){
                Log.e("Ayaz", result.toString())
                /*when(result){
                    is AsyncResult.Success ->{
                        val list = result.getDataOrNull()
                        firstMatchSlugTv.text = list?.get(1)?.firstTeamName +" vs "+ list?.get(1)?.secondTeamName
                    }
                    is AsyncResult.Error ->{
                        viewModel.errorHandlling(result.getExceptionOrNull()!!)
                    }
                    else ->{

                    }
                }*/
            }

        }
    }
    companion object{
        fun newInstance(count: Int?) = StatisticFragment().apply {
            arguments = Bundle().apply {
                if (count != null) {
                    putInt(ParamsKey.TEAM_ID, count)
                }
            }
        }
    }
}