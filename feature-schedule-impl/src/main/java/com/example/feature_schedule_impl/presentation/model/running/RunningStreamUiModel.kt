package com.example.feature_schedule_impl.presentation.model.running

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RunningStreamUiModel(
    val language:String,
    val url:String
):Parcelable
