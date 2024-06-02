package com.example.feature_search_impl.presentation.model.team

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TeamUiModel(
    val acronym: String?,
    val id: Int?,
    val imageUrl: String?,
    val location: String?,
    val name: String?,
    val players: List<PlayerUi>?,
    val slug: String?
):Parcelable