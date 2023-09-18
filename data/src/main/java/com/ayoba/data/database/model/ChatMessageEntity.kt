package com.ayoba.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Messages")
data class MessageEntity(
    @PrimaryKey(autoGenerate = true)
    val messageId: Int = 0,
    val chatId: Int,
    val message: String,
    val timeStamp: Long
)