package com.example.feature_search_impl.presentation.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.feature_search_impl.databinding.ItemHeadingBinding
import com.example.feature_search_impl.presentation.model.Heading
import com.nefrit.common.core.resources.ResourceManager
import com.nefrit.common.core.resources.ResourceManagerImpl

class HeadingHolder(
    private val viewBinding: ItemHeadingBinding,
    private val resourceManager: ResourceManager
):RecyclerView.ViewHolder(viewBinding.root) {
    fun bindItem(model:Heading){
        with(viewBinding){
            headingTv.text = resourceManager.getString(model.header)
        }
    }
}