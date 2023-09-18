package com.ayoba.data.model

import com.ayoba.data.database.model.ChatEntity
import com.ayoba.data.database.model.MessageEntity
import com.ayoba.model.data.Chat

fun ChatEntity.asExternalModel() = Chat(id = id, name = name)
fun MessageEntity.asExternalModel() = Chat.Message(
    id = messageId,
    chatId = chatId,
    message = message,
    timeStamp = timeStamp
)