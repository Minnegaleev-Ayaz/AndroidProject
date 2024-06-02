package com.example.feature_schedule_impl.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.feature_schedule_impl.databinding.ItemEmptyStreamsBinding
import com.example.feature_schedule_impl.databinding.ItemStreamBinding
import com.example.feature_schedule_impl.presentation.holder.EmptyStreamHolder
import com.example.feature_schedule_impl.presentation.holder.PastHolder
import com.example.feature_schedule_impl.presentation.holder.RunningHolder
import com.example.feature_schedule_impl.presentation.holder.StreamHolder
import com.example.feature_schedule_impl.presentation.holder.UpcomingHolder
import com.example.feature_schedule_impl.presentation.model.running.RunningStreamUiModel
import com.example.feature_schedule_impl.presentation.utils.StreamTypes

class StreamsAdapter(
    private val glide: RequestManager,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var streamsList = mutableListOf<RunningStreamUiModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> {
                StreamHolder(
                    viewBinding = ItemStreamBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ), glide = glide)
            }

            else -> {
                EmptyStreamHolder(
                    viewBinding = ItemEmptyStreamsBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return if (streamsList.size==0){
            1
        }else{
            streamsList.size
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is StreamHolder -> {
                holder.bindItem(streamsList[position])
            }

            is EmptyStreamHolder -> {
                holder.bindItem()
            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        if (streamsList.size>0 || streamsList==null){
            return StreamTypes.STREAM.number
        }else{
            return StreamTypes.EMPTY.number
        }
    }
}