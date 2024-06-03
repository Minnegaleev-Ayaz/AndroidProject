package com.example.feature_profile_api.domain.repository.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class DataUser(
    var userId: String? = null,
    var name: String? = null,
    var mail: String? = null
) {
    // Пустой конструктор для Firebase
    constructor() : this(null, null, null)
}