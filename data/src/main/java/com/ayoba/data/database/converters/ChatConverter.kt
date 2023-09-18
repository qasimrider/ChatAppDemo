package com.ayoba.data.database.converters

import com.ayoba.data.database.model.ChatEntity
import com.ayoba.data.database.model.MessageEntity
import com.ayoba.model.data.Chat

fun Chat.toRoomEntity(): ChatEntity = ChatEntity(id = id, name = name)

fun Chat.Message.toRoomEntity(): MessageEntity =
    MessageEntity(chatId = chatId, message = message, timeStamp = timeStamp)