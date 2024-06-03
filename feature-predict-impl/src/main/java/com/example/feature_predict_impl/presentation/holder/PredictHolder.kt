package com.example.feature_predict_impl.presentation.holder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.feature_predict_impl.databinding.ItemPredictBinding
import com.example.feature_predict_impl.presentation.model.MatchPresentationModel

class PredictHolder(
    private val viewBinding: ItemPredictBinding,
    private val glide:RequestManager,
    private val onStatisticClicked: ((MatchPresentationModel) -> Unit)? = null,
) : RecyclerView.ViewHolder(viewBinding.root) {
    var match:MatchPresentationModel? = null
    init{
        viewBinding.teamScoreIb.setOnClickListener {
            onStatisticClicked?.invoke(match!!)
        }
    }
    fun bindItem(matchPresentationModel: MatchPresentationModel) {
        match = matchPresentationModel
        with(viewBinding) {
            firstTeamNameTv.text = matchPresentationModel.firstTeamName
            secondTeamNameTv.text = matchPresentationModel.secondTeamName
            titleTv.text = matchPresentationModel.matchTime
            matchTypeTv.text = "bestOf "+matchPresentationModel.matchType
            leagueTv.text = matchPresentationModel.leagueName
            matchPresentationModel.firstTeamImage?.let {
                glide
                    .load(matchPresentationModel.firstTeamImage)
                    .into(firstTeamIv)
            }
            matchPresentationModel.secondTeamImage?.let {
                glide
                    .load(matchPresentationModel.secondTeamImage)
                    .into(secondTeamIv)
            }
        }
    }
}