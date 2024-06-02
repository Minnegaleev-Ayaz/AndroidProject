package com.example.feature_schedule_impl.presentation.model.upcoming

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UpcomingMatchUiModel (
    val id :Int?,
    val firstTeamId:Int?,
    val secondTeamId:Int?,
    val firstTeamName:String?,
    val secondTeamName:String?,
    val firstTeamImage:String?,
    val secondTeamImage:String?,
    val matchType: String?,
    val matchTime:String?,
    val leagueName:String?,
):Parcelable