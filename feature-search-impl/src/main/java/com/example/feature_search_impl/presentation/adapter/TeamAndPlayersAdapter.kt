package com.example.feature_search_impl.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.feature_search_impl.databinding.ItemHeadingBinding
import com.example.feature_search_impl.databinding.ItemPlayerBinding
import com.example.feature_search_impl.databinding.ItemTeamBinding
import com.example.feature_search_impl.presentation.diffutil.QuerryDiffUtill
import com.example.feature_search_impl.presentation.diffutil.ResponseDiffUtill
import com.example.feature_search_impl.presentation.holder.HeadingHolder
import com.example.feature_search_impl.presentation.holder.PlayerHolder
import com.example.feature_search_impl.presentation.holder.TeamHolder
import com.example.feature_search_impl.presentation.model.Heading
import com.example.feature_search_impl.presentation.model.player.PlayerUiModel
import com.example.feature_search_impl.presentation.model.team.TeamUiModel
import com.nefrit.common.core.resources.ResourceManager
import java.lang.IllegalArgumentException

class TeamAndPlayersAdapter(
    private val glide: RequestManager,
    private val onTeamClicked: ((TeamUiModel) -> Unit)?,
    private val onPlayerClicked: ((PlayerUiModel) -> Unit)?,
    private val resourceManager: ResourceManager
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items  = mutableListOf<Any>()
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> {
                PlayerHolder(
                    viewBinding = ItemPlayerBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    ),
                    glide = glide,
                    onPlayerClicked = onPlayerClicked,
                )
            }

            2 -> {
                TeamHolder(
                    viewBinding = ItemTeamBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    ),
                    glide = glide,
                    onTeamClicked = onTeamClicked
                )
            }

            3-> {
                HeadingHolder(
                    viewBinding = ItemHeadingBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    ),
                    resourceManager = resourceManager
                )
            }
            else ->{
                throw IllegalArgumentException()
            }


        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PlayerHolder -> {
                holder.bindItem(items[position] as PlayerUiModel)
            }

            is TeamHolder -> {
                holder.bindItem(items[position] as TeamUiModel)
            }

            is HeadingHolder -> {
                holder.bindItem(items[position] as Heading)
            }
        }
    }

    override fun getItemCount(): Int = items.size
    override fun getItemViewType(position: Int): Int {
        return when(items[position]){
            is PlayerUiModel -> TYPE_PLAYER
            is TeamUiModel -> TYPE_TEAM
            is Heading -> TYPE_HEADER
            else -> throw IllegalArgumentException()
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(list: List<Any>) {
        val diff = ResponseDiffUtill(oldItemsList = items, newItemsList = list)
        val diffResult = DiffUtil.calculateDiff(diff)
        items.clear()
        items.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }
    companion object{
        private const val TYPE_PLAYER = 1
        private const val TYPE_TEAM = 2
        private const val TYPE_HEADER = 3
    }
}