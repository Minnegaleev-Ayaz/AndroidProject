package com.example.feature_schedule_impl.domain.model

data class StreamDomainModel (
    val embedUrl: String?,
    val language: String,
    val main: Boolean?,
    val official: Boolean?,
    val rawUrl: String
)