package com.recan.photo_app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.recan.photo_app.data.model.PhotoDbEntity

@Database(entities = [PhotoDbEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoInfoDao
}