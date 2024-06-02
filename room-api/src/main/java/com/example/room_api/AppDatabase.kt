package com.example.room_api

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.room_api.dao.QuerryDao
import com.example.room_api.entity.QuerryEntity

@Database(entities = [QuerryEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun myQuerryDao(): QuerryDao
}