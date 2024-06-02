package com.example.room_api.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.room_api.entity.QuerryEntity

@Dao
interface QuerryDao {
    @Query("SELECT * FROM querry")
    fun getAll(): List<QuerryEntity>

    @Insert
    fun insert(myEntity: QuerryEntity)

    @Query("DELETE FROM querry WHERE id = :id")
    fun delete(id: Int)
}