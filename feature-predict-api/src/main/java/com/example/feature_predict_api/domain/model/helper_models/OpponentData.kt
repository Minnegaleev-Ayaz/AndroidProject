package com.example.feature_predict_api.domain.model.helper_models


import com.google.gson.annotations.SerializedName

data class OpponentData(
    @SerializedName("opponent")
    val opponent: OpponentX,
    @SerializedName("type")
    val type: String
)