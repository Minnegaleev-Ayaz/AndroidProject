package com.example.feature_predict_impl

import com.example.feature_predict_impl.presentation.model.MatchPresentationModel

interface PredictionFeatureRouter {
    fun openBottomFromMain(match:MatchPresentationModel)
}