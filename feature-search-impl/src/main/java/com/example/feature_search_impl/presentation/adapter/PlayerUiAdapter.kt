package com.example.feature_search_impl.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.feature_search_impl.databinding.ItemPlayerFromTeamBinding
import com.example.feature_search_impl.databinding.ItemQuerryBinding
import com.example.feature_search_impl.presentation.diffutil.QuerryDiffUtill
import com.example.feature_search_impl.presentation.holder.PlayerHolder
import com.example.feature_search_impl.presentation.holder.PlayerUiHolder
import com.example.feature_search_impl.presentation.holder.QuerryHolder
import com.example.feature_search_impl.presentation.model.querry.QuerryUiModel
import com.example.feature_search_impl.presentation.model.team.PlayerUi
import com.nefrit.common.core.resources.ResourceManager

class PlayerUiAdapter(
    private val onPLayerClicked: ((PlayerUi) -> Unit)? = null,
    private val resourceManager: ResourceManager,
    private val glide: RequestManager,
) : RecyclerView.Adapter<PlayerUiHolder>() {
    var items = mutableListOf<PlayerUi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerUiHolder {
        return PlayerUiHolder(
            ItemPlayerFromTeamBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            resourceManager = resourceManager,glide= glide, onPlayerClicked = onPLayerClicked
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PlayerUiHolder, position: Int) {
        holder.bindItem(items[position])
    }
}