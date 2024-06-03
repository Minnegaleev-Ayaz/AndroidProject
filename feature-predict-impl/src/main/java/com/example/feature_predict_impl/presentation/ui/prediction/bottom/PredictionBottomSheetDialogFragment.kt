package com.example.feature_predict_impl.presentation.ui.prediction.bottom

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.feature_predict_api.di.PredictFeatureApi
import com.example.feature_predict_impl.R
import com.example.feature_predict_impl.databinding.FragmentPredictBottomBinding
import com.example.feature_predict_impl.di.PredictionFeatureComponent
import com.example.feature_predict_impl.presentation.model.MatchPresentationModel
import com.example.feature_predict_impl.presentation.ui.prediction.prediction.PredictionFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nefrit.common.base.BaseBottomSheetDialogFragment
import com.nefrit.common.di.FeatureUtils
import com.nefrit.common.utils.AsyncResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class PredictionBottomSheetDialogFragment() :
    BaseBottomSheetDialogFragment(R.layout.fragment_predict_bottom) {
    @Inject
    lateinit var vmFactory: PredictionBottomSheetDialogAssistedViewModel.Factory
    private val viewModel: PredictionBottomSheetDialogAssistedViewModel by viewModels {
        PredictionBottomSheetDialogAssistedViewModel.provideFactory(
            vmFactory, assistedParam = arguments?.getParcelable<MatchPresentationModel>(
                PREDICT_BOTTOM_TAG
            )!!
        )
    }
    private val viewBinding: FragmentPredictBottomBinding by viewBinding(FragmentPredictBottomBinding::bind)
    override fun initViews() {
        val matchPresentationModel =  arguments?.getParcelable<MatchPresentationModel>(PREDICT_BOTTOM_TAG)!!
        setContentView(matchPresentationModel)
        subscribeButtons(viewModel)
        viewModel.initializeVoting()
        viewModel.initializeMyVote()
        lifecycleScope.launch{
            subscribeMyVotings(viewModel)
        }
        lifecycleScope.launch {
            subscribeVotings(viewModel)
        }
    }
    fun setContentView(match:MatchPresentationModel){
        with(viewBinding){
            Glide.with(this@PredictionBottomSheetDialogFragment).load(match.firstTeamImage).into(firstTeamIv)
            Glide.with(this@PredictionBottomSheetDialogFragment).load(match.secondTeamImage).into(secondTeamIv)
            firstTeamNameTv.text = match.firstTeamName
            secondTeamNameTv.text = match.secondTeamName
        }
    }
    fun  subscribeButtons(viewModel: PredictionBottomSheetDialogAssistedViewModel){
        with(viewBinding){
            voteForFirstBtn.setOnClickListener {
                lifecycleScope.launch {
                    viewModel.voting(0)
                }
                voteForFirstBtn.isEnabled = false
                voteForSecondBtn.isEnabled = false
                viewModel.initializeVoting()
            }
            voteForSecondBtn.setOnClickListener {
                lifecycleScope.launch {
                    viewModel.voting(1)
                }
                voteForFirstBtn.isEnabled = false
                voteForSecondBtn.isEnabled = false
                viewModel.initializeVoting()
            }
        }
    }
    suspend fun subscribeVotings(viewModel: PredictionBottomSheetDialogAssistedViewModel){
        viewModel.predictFlow.collect{ result->
            with(viewBinding){
                when(result){
                    is AsyncResult.Success ->{
                        val res = viewModel.calculatePercenatge(result.getDataOrNull()!!)
                        firstPercentageTv.text = res.first+"%"
                        secondPercentageTv.text = res.second+"%"
                    }
                    is AsyncResult.Error ->{

                    }
                    else ->{

                    }
                }
            }

        }
    }
    suspend fun subscribeMyVotings(viewModel: PredictionBottomSheetDialogAssistedViewModel){
        viewModel.myPredictFlow.collect{ result->
            with(viewBinding){
                when(result){
                    is AsyncResult.Success ->{
                        val res = result.getDataOrNull()
                        if (res!=null){
                            voteForFirstBtn.isEnabled = false
                            voteForSecondBtn.isEnabled = false
                            if (res.vote=="0"){
                                voteForFirstBtn.setBackgroundColor(resources.getColor(R.color.green))
                            }else{
                                voteForSecondBtn.setBackgroundColor(resources.getColor(R.color.green))
                            }
                        }
                    }
                    is AsyncResult.Error ->{
                    }
                    else ->{

                    }
                }
            }

        }
    }

    override fun inject() {
        FeatureUtils.getFeature<PredictionFeatureComponent>(this, PredictFeatureApi::class.java)
            .predictBottomComponentFactory().create(this).inject(this)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener { dialogInterface ->
            val d = dialogInterface as BottomSheetDialog
            val bottomSheet = d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
            val behavior = BottomSheetBehavior.from(bottomSheet)
            behavior.state = BottomSheetBehavior.STATE_COLLAPSED // Установите желаемое состояние
        }
        return dialog
    }

    companion object {
        const val PREDICT_BOTTOM_TAG = "PREDICT_BOTTOM_TAG"
        fun newInstance(game: MatchPresentationModel) = PredictionFragment().apply {
            arguments = Bundle().apply { putParcelable(PREDICT_BOTTOM_TAG, game) }
        }
    }
}