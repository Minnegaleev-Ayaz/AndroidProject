package com.example.feature_search_impl.presentation.holder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.feature_search_impl.R
import com.example.feature_search_impl.databinding.ItemTeamBinding
import com.example.feature_search_impl.presentation.model.player.PlayerUiModel
import com.example.feature_search_impl.presentation.model.team.TeamUiModel

class TeamHolder(
    private val viewBinding: ItemTeamBinding,
    private val glide: RequestManager,
    private val onTeamClicked: ((TeamUiModel) -> Unit)? = null
) : RecyclerView.ViewHolder(viewBinding.root) {
    var teamModel:TeamUiModel? = null
    init {
        viewBinding.root.setOnClickListener {
            onTeamClicked?.invoke(teamModel!!)
        }
    }

    fun bindItem(team: TeamUiModel) {
        teamModel=team
        with(viewBinding) {
            nameTv.text = team.name
            if(team.imageUrl!=null){
                glide.load(team.imageUrl).into(teamImageIv)
            }else{
                teamImageIv.setImageResource(R.drawable.team_icon)
            }
        }
    }
}