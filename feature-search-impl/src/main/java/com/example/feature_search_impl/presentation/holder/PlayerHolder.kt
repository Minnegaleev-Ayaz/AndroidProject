package com.example.feature_search_impl.presentation.holder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.feature_search_impl.R
import com.example.feature_search_impl.databinding.ItemPlayerBinding
import com.example.feature_search_impl.presentation.model.player.PlayerUiModel

class PlayerHolder(
    private val viewBinding: ItemPlayerBinding,
    private val glide: RequestManager,
    private val onPlayerClicked: ((PlayerUiModel) -> Unit)? = null
) : RecyclerView.ViewHolder(viewBinding.root) {
    private var playerModel: PlayerUiModel? = null
    init {
        viewBinding.root.setOnClickListener {
            playerModel?.let { it1 -> onPlayerClicked?.invoke(it1) }
        }
    }

    fun bindItem(player: PlayerUiModel) {
        with(viewBinding) {
            playerModel = player

            nameTv.text = player.name
            teamNameTv.text = player.teamName
            if(player.imageUrl!=null){
                glide.load(player.imageUrl).into(playerImageIv)
            }else{
                playerImageIv.setImageResource(R.drawable.user_icon)
            }
        }
    }
}