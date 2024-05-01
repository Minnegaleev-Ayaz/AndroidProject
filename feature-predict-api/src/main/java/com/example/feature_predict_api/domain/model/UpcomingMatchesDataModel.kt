package com.example.feature_predict_api.domain.model


import com.example.feature_predict_api.domain.model.helper_models.OpponentData
import com.example.feature_predict_api.domain.model.helper_models.SerieData
import com.example.feature_predict_api.domain.model.helper_models.TournamentData
import com.google.gson.annotations.SerializedName

data class UpcomingMatchesDataModel(
    @SerializedName("begin_at")
    val beginAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("match_type")
    val matchType: String?,
    @SerializedName("modified_at")
    val modifiedAt: String?,
    @SerializedName("number_of_games")
    val numberOfGames: Int?,
    @SerializedName("opponents")
    val opponents: List<OpponentData>,
    @SerializedName("serie")
    val serie: SerieData?,
    @SerializedName("tournament")
    val tournament: TournamentData,

)