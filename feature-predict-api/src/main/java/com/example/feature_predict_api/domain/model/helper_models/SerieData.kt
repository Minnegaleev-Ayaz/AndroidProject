package com.example.feature_predict_api.domain.model.helper_models


import com.google.gson.annotations.SerializedName

data class SerieData(
    @SerializedName("begin_at")
    val beginAt: String?,
    @SerializedName("end_at")
    val endAt: String?,
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("league_id")
    val leagueId: Int?,
    @SerializedName("modified_at")
    val modifiedAt: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("season")
    val season: String?,
    @SerializedName("year")
    val year: Int?
)