package com.ayoba.data

import com.ayoba.data.core.result.Result
import com.ayoba.data.core.result.asResult
import com.ayoba.data.database.converters.toRoomEntity
import com.ayoba.data.database.dao.ChatDao
import com.ayoba.data.model.asExternalModel
import com.ayoba.model.data.Chat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class ChatRepositoryImpl @Inject constructor(private val chatDao: ChatDao) : ChatRepository {
    override suspend fun insertChat(chat: Chat): Flow<Result<Unit>> {
        //remove usedcase DI
        return flow { emit(chatDao.insertChat(chat.toRoomEntity())) }.asResult()
    }

    override suspend fun insertMessage(chatMessage: Chat.Message): Flow<Result<Unit>> {
        return flow { emit(chatDao.insertMessage(chatMessage.toRoomEntity())) }.asResult()
    }

    override fun getAllChats(): Flow<Result<List<Chat>>> {
        return chatDao.getAllChats().map { it.map { it.asExternalModel() } }.asResult()
    }

    override fun getAllMessages(chatId: Int): Flow<Result<List<Chat.Message>>> {
        return chatDao.getAllMessages(chatId).map { it.map { it.asExternalModel() } }.asResult()
    }

}