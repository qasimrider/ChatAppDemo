package com.ayoba.domain.useCase

import com.ayoba.data.ChatRepository
import com.ayoba.data.core.result.Result
import com.ayoba.domain.base.BaseUseCase
import com.ayoba.model.data.Chat
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetInsertMessagesUseCase @Inject constructor(private val chatRepository: ChatRepository) :
    BaseUseCase<Unit, GetInsertMessagesUseCase.Params>() {
    override suspend fun run(param: Params): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun runFlow(param: Params): Flow<Result<Unit>> {
        return chatRepository.insertMessage(param.run {
            Chat.Message(
                message = message,
                chatId = chatId,
                timeStamp = timeStamp
            )
        }
        )
    }

    class Params(val message: String, val chatId: Int, val timeStamp: Long)
}