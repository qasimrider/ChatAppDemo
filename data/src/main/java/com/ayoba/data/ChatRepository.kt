package com.ayoba.data

import com.ayoba.data.core.result.Result
import com.ayoba.model.data.Chat
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun insertChat(chat: Chat): Flow<Result<Unit>>
    suspend fun insertMessage(chatMessage: Chat.Message): Flow<Result<Unit>>
    fun getAllChats(): Flow<Result<List<Chat>>>
    fun getAllMessages(chatId: Int): Flow<Result<List<Chat.Message>>>
}