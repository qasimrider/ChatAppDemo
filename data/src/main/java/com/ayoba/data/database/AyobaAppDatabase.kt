package com.ayoba.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ayoba.data.database.dao.ChatDao
import com.ayoba.data.database.model.ChatEntity
import com.ayoba.data.database.model.MessageEntity

@Database(
    entities = [ChatEntity::class, MessageEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AyobaAppDatabase : RoomDatabase() {
    abstract fun chatDao(): ChatDao
}
