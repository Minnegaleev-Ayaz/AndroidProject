package com.nefrit.common.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.OffsetTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

class DateFormatter {

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val outputFormatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy", Locale.getDefault())

    fun formatDate(date: String): String {
        val dates = LocalDateTime.parse(date,formatter).plusHours(3)
        return   dates.format(outputFormatter)
    }
}