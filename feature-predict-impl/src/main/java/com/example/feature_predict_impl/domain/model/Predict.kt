package com.example.feature_predict_impl.domain.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Predict(
    var userId: String? = null,
    var matchId: String? = null,
    var vote: String? = null
) {
    // Пустой конструктор для Firebase
    constructor() : this(null, null, null)
}