package com.example.feature_schedule_api.domain.model


import com.google.gson.annotations.SerializedName

data class Serie(
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
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("winner_id")
    val winnerId: Any?,
    @SerializedName("winner_type")
    val winnerType: String?,
    @SerializedName("year")
    val year: Int?
)