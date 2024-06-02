package com.example.feature_search_impl.presentation.model.player

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable
@Parcelize
data class PlayerUiModel(
    val active: Boolean?,
    val firstName: String?,
    val id: Int?,
    val imageUrl: String?,
    val lastName: String?,
    val name: String?,
    val nationality: String?,
    val role: String?,
    val slug: String?,
    val teamImageUrl:String?,
    val teamName:String?
):Parcelable