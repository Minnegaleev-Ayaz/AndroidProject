package com.example.feature_search_impl.presentation.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.feature_search_impl.presentation.model.querry.QuerryUiModel

class QuerryDiffUtill(
    private val oldItemsList: MutableList<QuerryUiModel>,
    private val newItemsList: List<QuerryUiModel>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldItemsList.size

    override fun getNewListSize(): Int = newItemsList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItemsList[oldItemPosition]
        val newItem = newItemsList[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItemsList[oldItemPosition]
        val newItem = newItemsList[newItemPosition]

        return (oldItem.text == newItem.text)
    }

}