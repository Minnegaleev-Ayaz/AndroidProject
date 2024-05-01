package com.example.feature_predict_impl.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.feature_predict_impl.databinding.ItemPredictBinding
import com.example.feature_predict_impl.presentation.holder.PredictHolder
import com.example.feature_predict_impl.presentation.model.MatchPresentationModel

class PredictAdapter(
    private val glide: RequestManager,
    private val onStatisticClicked: ((Int) -> Unit)? = null,
    private val onScoreClickes: ((Int) -> Boolean)? = null
) : RecyclerView.Adapter<PredictHolder>() {
    var items:List<MatchPresentationModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PredictHolder {
        return PredictHolder(
            viewBinding = ItemPredictBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            glide = glide,
            onStatisticClicked = onStatisticClicked,
            onScoreClickes = onScoreClickes
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PredictHolder, position: Int) {
        holder.bindItem(items[position])
    }
}