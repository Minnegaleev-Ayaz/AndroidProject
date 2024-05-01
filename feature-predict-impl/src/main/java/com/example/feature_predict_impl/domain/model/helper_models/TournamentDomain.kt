package com.example.feature_predict_impl.domain.model.helper_models

import com.google.gson.annotations.SerializedName

data class TournamentDomain (
    val beginAt: String?,
    val endAt: String?,
    val id: Int?,
    val leagueId: Int?,
    val name: String?,
    val serieId: Int?,
    val slug: String?,
    val tier: String?,
)