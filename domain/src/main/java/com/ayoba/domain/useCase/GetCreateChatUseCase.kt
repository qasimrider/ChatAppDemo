package com.ayoba.domain.useCase

import com.ayoba.data.ChatRepository
import com.ayoba.data.core.result.Result
import com.ayoba.domain.base.BaseUseCase
import com.ayoba.model.data.Chat
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetCreateChatUseCase @Inject constructor(private val chatRepository: ChatRepository) :
    BaseUseCase<Unit, GetCreateChatUseCase.Params>() {
    override suspend fun run(param: Params): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun runFlow(param: Params): Flow<Result<Unit>> {
        return chatRepository.insertChat(Chat(name = param.chatName))
    }

    class Params(val chatName: String)
}