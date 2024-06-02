package com.example.feature_schedule_impl

import com.example.feature_schedule_impl.presentation.model.running.RunningMatchUiModel
import com.example.feature_schedule_impl.presentation.model.upcoming.UpcomingMatchUiModel

interface ScheduleFeatureRouter {
    fun openStreamsFromRunning(model:RunningMatchUiModel)
    fun openUpcomingBottom(model:UpcomingMatchUiModel)
}