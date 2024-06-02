package com.example.feature_predict_impl.presentation.ui.prediction.prediction

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.feature_predict_api.di.PredictFeatureApi
import com.example.feature_predict_impl.R
import com.example.feature_predict_impl.databinding.AlertDialogStatisticBinding
import com.example.feature_predict_impl.databinding.FragmentLastMatchesViewPagerBinding
import com.example.feature_predict_impl.databinding.FragmentPredictionsBinding
import com.example.feature_predict_impl.di.PredictionFeatureComponent
import com.example.feature_predict_impl.presentation.adapter.PredictAdapter
import com.example.feature_predict_impl.presentation.model.MatchPresentationModel
import com.example.feature_predict_impl.presentation.ui.prediction.last_matches_statistic.StatisticFragment
import com.nefrit.common.base.BaseFragment
import com.nefrit.common.di.FeatureUtils
import com.nefrit.common.utils.AsyncResult
import com.nefrit.common.utils.ParamsKey
import dagger.Lazy
import kotlinx.coroutines.launch
import javax.inject.Inject

class PredictionFragment : BaseFragment(R.layout.fragment_predictions) {
    @Inject
    lateinit var factory: Lazy<ViewModelProvider.Factory>
    private val viewModel: PredictionViewModel by viewModels { factory.get() }
    private val viewBinding: FragmentPredictionsBinding by viewBinding(FragmentPredictionsBinding::bind)
    private var rvAdapter: PredictAdapter? = null
    private val statsViewBinding: AlertDialogStatisticBinding by viewBinding(
        AlertDialogStatisticBinding::bind)

    override fun initViews() {
        lifecycleScope.launch {
            subscribeUncomingMatches(viewModel)
            subscribeErrors(viewModel)
        }
        with(viewBinding) {
            val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            predictionsRv.layoutManager = layoutManager
            rvAdapter = PredictAdapter(
                glide = Glide.with(this@PredictionFragment),
                onStatisticClicked = { pos -> onStatisticCliked(pos) },
                onScoreClickes = null,
            )
            predictionsRv.adapter = rvAdapter
            viewModel.initialize()
        }
    }

    fun onStatisticCliked(pos: Int) {
        var match = rvAdapter?.items?.get(pos)
        val alertDialog = AlertDialog.Builder(requireContext()).setTitle("Last 5 Matches")
            .setView(R.layout.alert_dialog_statistic).create()
        alertDialog.show()
        val viewPager = alertDialog.findViewById<ViewPager2>(R.id.stats_VP)
        viewPager.adapter = createViewPagerAdapter(match!!)
    }


    override fun inject() {
        FeatureUtils.getFeature<PredictionFeatureComponent>(this, PredictFeatureApi::class.java)
            .predictionComponentFactory().create(this).inject(this)

    }

    suspend fun subscribeUncomingMatches(viewModel: PredictionViewModel) {
        viewModel.uncomingMatchesFlow.collect { result ->
            with(viewBinding) {
                when (result) {
                    is AsyncResult.Loading -> {
                        errorTv.visibility = View.INVISIBLE
                        predictionsRv.visibility = View.INVISIBLE
                        loadingPb.visibility = View.VISIBLE
                    }

                    is AsyncResult.Success -> {
                        predictionsRv.visibility = View.VISIBLE
                        loadingPb.visibility = View.INVISIBLE
                        rvAdapter?.items = result.getDataOrNull()!!
                        predictionsRv.adapter = rvAdapter
                    }

                    is AsyncResult.Error -> {
                        loadingPb.visibility = View.VISIBLE
                        errorTv.text = result.getExceptionOrNull()!!.message
                        viewModel.errorHandlling(result.getExceptionOrNull()!!)
                    }

                    else -> {
                    }
                }
            }
        }
    }
    suspend fun subscribeErrors(viewModel: PredictionViewModel){
        viewModel.errorFlow.collect {
            it?.message?.let { it1 -> showToast(it1) }
        }
    }

    fun createViewPagerAdapter(match: MatchPresentationModel): RecyclerView.Adapter<*>? {
        val team_list = listOf(match.firstTeamId, match.secondTeamId)
        val viewPager = object : FragmentStateAdapter(this) {
            override fun getItemCount() = team_list.size

            override fun createFragment(position: Int) =
                StatisticFragment.newInstance(team_list[position])
        }
        return viewPager
    }

    companion object {
        fun newInstance(firstTeamId: Int, secondTeamId: Int) = PredictionFragment().apply {
            arguments = Bundle().apply {
                putInt(ParamsKey.FIRST_TEAM_ID_TAG, firstTeamId)
                putInt(ParamsKey.SECOND_TEAM_ID_TAG, secondTeamId)
            }
        }
    }
}