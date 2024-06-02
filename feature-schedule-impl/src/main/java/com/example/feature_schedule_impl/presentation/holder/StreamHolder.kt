package com.example.feature_schedule_impl.presentation.holder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.RequestManager
import com.example.feature_schedule_impl.databinding.ItemStreamBinding
import com.example.feature_schedule_impl.presentation.model.running.RunningStreamUiModel
import com.nefrit.common.core.resources.ResourceManager

class StreamHolder(
    private val viewBinding: ItemStreamBinding,
    private val glide: RequestManager
):RecyclerView.ViewHolder(viewBinding.root) {
    fun bindItem(stream:RunningStreamUiModel){
        with(viewBinding){
            languageTv.text = stream.language+":"
            urlTv.text = stream.url
        }
    }
}