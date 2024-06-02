package com.example.feature_search_impl.presentation.holder


import androidx.recyclerview.widget.RecyclerView
import com.example.feature_search_impl.databinding.ItemQuerryBinding
import com.example.feature_search_impl.presentation.model.player.PlayerUiModel
import com.example.feature_search_impl.presentation.model.querry.QuerryUiModel

class QuerryHolder(
    private val viewBinding: ItemQuerryBinding,
    private val onRootClicked: ((QuerryUiModel) -> Unit)? = null,
    private val onCrossClicked: ((QuerryUiModel)->Unit)? = null
) : RecyclerView.ViewHolder(viewBinding.root) {
    private lateinit var currentQuerry:QuerryUiModel
    init {
        viewBinding.crossIb.setOnClickListener{
            onCrossClicked?.invoke(currentQuerry)
        }
        viewBinding.root.setOnClickListener {
            onRootClicked?.invoke(currentQuerry)
        }
    }

    fun bindItem(querry: QuerryUiModel) {
        currentQuerry = querry
        with(viewBinding) {
            querryTv.text = querry.text
        }
    }
}