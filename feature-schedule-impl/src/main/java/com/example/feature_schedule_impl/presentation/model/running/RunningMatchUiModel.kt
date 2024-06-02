package com.example.feature_schedule_impl.presentation.model.running

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RunningMatchUiModel(
    val id: Int?,
    val firstTeamName: String?,
    val secondTeamName: String?,
    val firstTeamImage: String?,
    val secondTeamImage: String?,
    val matchType: String?,
    val matchTime: String?,
    val leagueName: String?,
    val streams: List<RunningStreamUiModel>
) : Parcelable