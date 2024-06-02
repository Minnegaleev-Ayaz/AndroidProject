package com.example.feature_schedule_impl.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.feature_schedule_impl.databinding.ItemPastMatchBinding
import com.example.feature_schedule_impl.databinding.ItemRunningMatchBinding
import com.example.feature_schedule_impl.databinding.ItemUpcomingMatchBinding
import com.example.feature_schedule_impl.presentation.holder.PastHolder
import com.example.feature_schedule_impl.presentation.holder.RunningHolder
import com.example.feature_schedule_impl.presentation.holder.UpcomingHolder
import com.example.feature_schedule_impl.presentation.model.past.PastMatchUiModel
import com.example.feature_schedule_impl.presentation.model.running.RunningMatchUiModel
import com.example.feature_schedule_impl.presentation.model.upcoming.UpcomingMatchUiModel
import com.example.feature_schedule_impl.presentation.utils.ItemTypes

class MatchAdapter(
    private val glide:RequestManager,
    private val onStreamsClicked: ((RunningMatchUiModel) -> Unit)?,
    private val onStatsClicked: ((UpcomingMatchUiModel) -> Unit)?,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var pastList = mutableListOf<PastMatchUiModel>()
    var runningList = mutableListOf<RunningMatchUiModel>()
    var upcomingList = mutableListOf<UpcomingMatchUiModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> {
                PastHolder(
                    viewBinding = ItemPastMatchBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    ),
                    glide = glide,
                )
            }

            2 -> {
                RunningHolder(
                    viewBinding = ItemRunningMatchBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    ),
                    glide = glide,
                    onStreamsClicked = onStreamsClicked
                )
            }

            else -> {
                UpcomingHolder(
                    viewBinding = ItemUpcomingMatchBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    ),
                    glide = glide,
                    onStatsClicked = onStatsClicked
                )
            }


        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PastHolder -> {
                holder.bindItem(pastList[position])
            }

            is RunningHolder -> {
                holder.bindItem(runningList[position])
            }

            is UpcomingHolder -> {
                holder.bindItem(upcomingList[position])
            }
        }
    }

    override fun getItemCount(): Int = pastList.size+upcomingList.size+runningList.size
    override fun getItemViewType(position: Int): Int {
        if (pastList.size==0 && upcomingList.size==0){
            return ItemTypes.RUNNING.number
        }
        if (runningList.size==0 && upcomingList.size==0){
            return ItemTypes.PAST.number
        }
        if (runningList.size==0 && pastList.size==0){
            return ItemTypes.UPCOMING.number
        }
        return 0
    }

}