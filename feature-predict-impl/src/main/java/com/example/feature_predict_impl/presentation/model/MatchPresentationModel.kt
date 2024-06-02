package com.example.feature_predict_impl.presentation.model

data class MatchPresentationModel(
    val id :Int?,
    val firstTeamId:Int?,
    val secondTeamId:Int?,
    val firstTeamName:String?,
    val secondTeamName:String?,
    val firstTeamImage:String?,
    val secondTeamImage:String?,
    val matchType: Int?,
    val matchTime:String?,
    val leagueName:String?
)
