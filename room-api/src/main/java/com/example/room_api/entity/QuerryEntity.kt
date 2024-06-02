package com.example.room_api.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.room_api.converters.DateConverter
import java.time.LocalDate

@Entity(tableName = "querry")
@TypeConverters(DateConverter::class)
data class QuerryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val text: String,
)