package com.example.feature_schedule_impl.presentation.holder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.feature_schedule_impl.databinding.ItemPastMatchBinding
import com.example.feature_schedule_impl.databinding.ItemRunningMatchBinding
import com.example.feature_schedule_impl.presentation.model.past.PastMatchUiModel
import com.example.feature_schedule_impl.presentation.model.running.RunningMatchUiModel

class RunningHolder(
    private val viewBinding: ItemRunningMatchBinding,
    private val glide: RequestManager,
    private val onStreamsClicked: ((RunningMatchUiModel) -> Unit)? = null
) : RecyclerView.ViewHolder(viewBinding.root) {
    private lateinit var runningMatchUiModel:RunningMatchUiModel
    init {
        viewBinding.root.setOnClickListener {
            onStreamsClicked?.invoke(runningMatchUiModel)
        }
    }

    fun bindItem(pastMatch: RunningMatchUiModel) {
        runningMatchUiModel = pastMatch
        with(viewBinding) {
            firstTeamNameTv.text = pastMatch.firstTeamName
            secondTeamNameTv.text = pastMatch.secondTeamName
            matchTypeTv.text = pastMatch.matchType
            titleTv.text = pastMatch.matchTime
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