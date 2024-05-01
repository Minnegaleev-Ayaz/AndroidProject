package com.example.feature_predict_impl.presentation.ui.prediction

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.feature_predict_api.di.PredictFeatureApi
import com.example.feature_predict_impl.R
import com.example.feature_predict_impl.databinding.FragmentPredictionsBinding
import com.example.feature_predict_impl.di.PredictionFeatureComponent
import com.example.feature_predict_impl.presentation.adapter.PredictAdapter
import com.example.feature_predict_impl.presentation.model.MatchPresentationModel
import com.nefrit.common.base.BaseFragment
import com.nefrit.common.di.FeatureUtils
import com.nefrit.common.utils.AsyncResult

class PredictionFragment : BaseFragment<PredictionViewModel>(R.layout.fragment_predictions) {
    private val viewBinding: FragmentPredictionsBinding by viewBinding(FragmentPredictionsBinding::bind)
    private var rvAdapter: PredictAdapter? = null

    override fun initViews() {
        with(viewBinding) {
            val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            predictionsRv.layoutManager = layoutManager
            rvAdapter = PredictAdapter(
                glide = Glide.with(this@PredictionFragment),
                onStatisticClicked = null,
                onScoreClickes = null,
            )
            //rvAdapter?.items = mutableListOf(MatchPresentationModel(1,"qwert","asdf","https://avatars.mds.yandex.net/i?id=f4a5869795c7b15cc66f7c081d84d9a5e959d989-10310748-images-thumbs&n=13","https://avatars.mds.yandex.net/i?id=f4a5869795c7b15cc66f7c081d84d9a5e959d989-10310748-images-thumbs&n=13","bo3","12:00"))
            predictionsRv.adapter = rvAdapter
            rvAdapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initialize()
    }

    override fun inject() {
        FeatureUtils.getFeature<PredictionFeatureComponent>(this, PredictFeatureApi::class.java)
            .predictionComponentFactory().create(this).inject(this)

    }

    override suspend fun subscribe(viewModel: PredictionViewModel) {
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
        viewModel.errorFlow.collect {
            it?.message?.let { it1 -> showToast(it1) }
        }
    }
}