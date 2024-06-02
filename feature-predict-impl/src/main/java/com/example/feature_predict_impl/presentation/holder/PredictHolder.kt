package com.example.feature_predict_impl.presentation.holder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.feature_predict_impl.databinding.ItemPredictBinding
import com.example.feature_predict_impl.presentation.model.MatchPresentationModel

class PredictHolder(
    private val viewBinding: ItemPredictBinding,
    private val glide:RequestManager,
    private val onStatisticClicked: ((Int) -> Unit)? = null,
    private val onScoreClickes: ((Int) -> Boolean)? = null
) : RecyclerView.ViewHolder(viewBinding.root) {
    init{
        viewBinding.statsBtn.setOnClickListener {
            onStatisticClicked?.invoke(adapterPosition)
        }
    }
    fun bindItem(matchPresentationModel: MatchPresentationModel) {
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