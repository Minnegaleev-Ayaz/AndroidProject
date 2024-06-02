package com.example.feature_search_impl.presentation.model.team

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlayerUi(
    val active: Boolean?,
    val age: Int?,
    val birthday: String?,
    val firstName: String?,
    val id: Int?,
    val imageUrl: String?,
    val lastName: String?,
    val name: String?,
    val nationality: String?,
    val role: String?,
    val slug: String?
):Parcelable