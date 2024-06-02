package com.example.feature_schedule_impl.presentation.holder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.feature_schedule_impl.databinding.ItemUpcomingMatchBinding
import com.example.feature_schedule_impl.presentation.model.upcoming.UpcomingMatchUiModel

class UpcomingHolder(
    private val viewBinding: ItemUpcomingMatchBinding,
    private val glide: RequestManager,
    private val onStatsClicked: ((UpcomingMatchUiModel) -> Unit)? = null
) : RecyclerView.ViewHolder(viewBinding.root) {
    private lateinit var upcomingMatchUiModel: UpcomingMatchUiModel
    init {
        viewBinding.root.setOnClickListener {
            onStatsClicked?.invoke(upcomingMatchUiModel)
        }
    }
    fun bindItem(pastMatch: UpcomingMatchUiModel) {
        upcomingMatchUiModel = pastMatch
        with(viewBinding) {
            firstTeamNameTv.text = pastMatch.firstTeamName
            secondTeamNameTv.text = pastMatch.secondTeamName
            matchTypeTv.text = pastMatch.matchType
            titleTv.text = pastMatch.matchTime
            matchTypeTv.text = pastMatch.matchType
            leagueTv.text = pastMatch.leagueName
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