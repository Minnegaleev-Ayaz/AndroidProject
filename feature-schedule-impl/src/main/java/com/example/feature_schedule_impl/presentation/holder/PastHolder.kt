package com.example.feature_schedule_impl.presentation.holder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.feature_schedule_impl.databinding.ItemPastMatchBinding
import com.example.feature_schedule_impl.presentation.model.past.PastMatchUiModel

class PastHolder(
    private val viewBinding: ItemPastMatchBinding,
    private val glide: RequestManager,
) : RecyclerView.ViewHolder(viewBinding.root) {
    init {
        viewBinding.root.setOnClickListener {
        }
    }

    fun bindItem(pastMatch: PastMatchUiModel) {
        with(viewBinding) {
            firstTeamNameTv.text = pastMatch.firstTeamName
            secondTeamNameTv.text = pastMatch.secondTeamName
            if(pastMatch.matchType!!%2==0){
                matchTypeTv.text = "bestOf"+(pastMatch.matchType+1).toString()
            }
            matchTypeTv.text = "bestOf"+pastMatch.matchType.toString()
            titleTv.text = pastMatch.matchTime?:"qweqwe"
            leagueTv.text = pastMatch.leagueName
            firstTeamScoreBtn.text = pastMatch.firstTeamScore.toString()
            secondTeamScoreBtn.text = pastMatch.secondTeamScore.toString()

            pastMatch.firstTeamImage?.let {
                glide
                    .load(pastMatch.firstTeamImage)
                    .into(firstTeamIv)
            }
            pastMatch.secondTeamImage?.let {
                glide
                    .load(pastMatch.secondTeamImage)
                    .into(secondTeamIv)
            }
        }
    }
}