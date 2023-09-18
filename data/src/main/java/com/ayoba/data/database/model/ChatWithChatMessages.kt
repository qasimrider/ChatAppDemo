package com.ayoba.data.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class ChatWithChatMessages(
    @Embedded
    val chat: ChatEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "chatId"
    )
    val messages: List<MessageEntity>
)