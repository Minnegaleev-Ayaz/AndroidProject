package com.example.feature_search_impl.presentation.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.feature_search_impl.presentation.model.Heading
import com.example.feature_search_impl.presentation.model.player.PlayerUiModel
import com.example.feature_search_impl.presentation.model.querry.QuerryUiModel
import com.example.feature_search_impl.presentation.model.team.TeamUiModel

class ResponseDiffUtill(
    private val oldItemsList: List<Any>,
    private val newItemsList: List<Any>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldItemsList.size

    override fun getNewListSize(): Int = newItemsList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItemsList[oldItemPosition]
        val newItem = newItemsList[newItemPosition]
        return when {
            oldItem is Heading && newItem is Heading -> oldItem.header == newItem.header
            oldItem is PlayerUiModel && newItem is PlayerUiModel -> oldItem.id == newItem.id
            oldItem is TeamUiModel && newItem is TeamUiModel -> oldItem.id == newItem.id
            else -> false
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItemsList[oldItemPosition]
        val newItem = newItemsList[newItemPosition]
        return when {
            oldItem is Heading && newItem is Heading -> oldItem.header == newItem.header
            oldItem is PlayerUiModel && newItem is PlayerUiModel ->  oldItem.name == newItem.name && oldItem.active == newItem.active && oldItem.firstName == newItem.firstName && oldItem.imageUrl == newItem.imageUrl && oldItem.lastName == newItem.lastName && oldItem.nationality == newItem.nationality && oldItem.role == newItem.role && oldItem.slug == newItem.slug && oldItem.teamImageUrl == newItem.teamImageUrl && oldItem.teamName == newItem.teamName
            oldItem is TeamUiModel && newItem is TeamUiModel -> oldItem.name == newItem.name && oldItem.acronym == newItem.acronym && oldItem.imageUrl == newItem.imageUrl && oldItem.location == newItem.location && oldItem.slug == newItem.slug
            else -> false
        }

    }

}