package com.ayoba.domain.useCase

import com.ayoba.data.ChatRepository
import com.ayoba.data.core.result.Result
import com.ayoba.domain.base.BaseUseCase
import com.ayoba.model.data.Chat
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetAllMessagesUseCase @Inject constructor(private val chatRepository: ChatRepository) :
    BaseUseCase<List<Chat.Message>, GetAllMessagesUseCase.Params>() {

    override suspend fun run(param: Params): Result<List<Chat.Message>> {
        TODO("Not yet implemented")
    }

    override suspend fun runFlow(param: Params): Flow<Result<List<Chat.Message>>> {
        return chatRepository.getAllMessages(param.chatId)
    }

    data class Params(val chatId: Int)
}
