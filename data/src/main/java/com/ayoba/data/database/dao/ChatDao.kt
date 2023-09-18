package com.ayoba.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.ayoba.data.database.model.ChatEntity
import com.ayoba.data.database.model.ChatWithChatMessages
import com.ayoba.data.database.model.MessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {
    @Insert
    fun insertChat(chat: ChatEntity)

    @Insert
    fun insertMessage(message: MessageEntity)

    @Query("SELECT * FROM Chat ORDER BY name ASC")
    fun getAllChats(): Flow<List<ChatEntity>>

    @Transaction
    @Query("SELECT * FROM Chat")
    fun getChatWithMessages(): List<ChatWithChatMessages>

    @Query("SELECT * FROM Messages WHERE chatId = :chatId")
    fun getAllMessages(chatId: Int): Flow<List<MessageEntity>>
}