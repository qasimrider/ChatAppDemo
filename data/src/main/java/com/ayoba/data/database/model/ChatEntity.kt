package com.ayoba.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Chat")
data class ChatEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
//    @Embedded
//    val messages: List<ChatMessageEntity>
)
