package com.example.feature_search_impl.presentation.holder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.feature_search_impl.R
import com.example.feature_search_impl.databinding.ItemPlayerBinding
import com.example.feature_search_impl.databinding.ItemPlayerFromTeamBinding
import com.example.feature_search_impl.presentation.model.team.PlayerUi
import com.nefrit.common.core.resources.ResourceManager

class PlayerUiHolder(
    private val viewBinding: ItemPlayerFromTeamBinding,
    private val glide: RequestManager,
    private val onPlayerClicked: ((PlayerUi) -> Unit)? = null,
    private val resourceManager: ResourceManager
) : RecyclerView.ViewHolder(viewBinding.root) {
    private var playerModel: PlayerUi? = null
    init {
        viewBinding.root.setOnClickListener {
            playerModel?.let { it1 -> onPlayerClicked?.invoke(it1) }
        }
    }

    fun bindItem(player: PlayerUi) {
        with(viewBinding) {
            playerModel = player
            nameTv.text = player.name
            firstLastNameTv.text = resourceManager.getString(R.string.first_last_name,player.firstName.toString(),player.lastName.toString())
            if(player.imageUrl!=null){
                glide.load(player.imageUrl).into(playerImageIv)
            }else{
                playerImageIv.setImageResource(R.drawable.user_icon)
            }

        }
    }
}