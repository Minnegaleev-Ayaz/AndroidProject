package com.example.room_api

import android.content.Context
import androidx.room.Room
import com.nefrit.common.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class RoomDatabaseModule {
    @ApplicationScope
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "database-name")
            .build()
    }
}