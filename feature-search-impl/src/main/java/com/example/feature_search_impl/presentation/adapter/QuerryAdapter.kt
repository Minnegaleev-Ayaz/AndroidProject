package com.example.feature_search_impl.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_search_impl.databinding.ItemQuerryBinding
import com.example.feature_search_impl.presentation.diffutil.QuerryDiffUtill
import com.example.feature_search_impl.presentation.holder.QuerryHolder
import com.example.feature_search_impl.presentation.model.querry.QuerryUiModel

class QuerryAdapter(
    private val onRootClicked: ((QuerryUiModel) -> Unit)? = null,
    private val onCrossClicked: ((QuerryUiModel) -> Unit)? = null
) : RecyclerView.Adapter<QuerryHolder>() {
    var items = mutableListOf<QuerryUiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuerryHolder {
        return QuerryHolder(
            ItemQuerryBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onRootClicked, onCrossClicked
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: QuerryHolder, position: Int) {
        holder.bindItem(items[position])
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(list:List<QuerryUiModel>) {
        val diff = QuerryDiffUtill(oldItemsList = items, newItemsList = list)
        val diffResult = DiffUtil.calculateDiff(diff)
        items.clear()
        items.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }
}