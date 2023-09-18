package com.ayoba.domain.useCase

import com.ayoba.data.ChatRepository
import com.ayoba.data.core.result.Result
import com.ayoba.domain.base.BaseUseCase
import com.ayoba.model.data.Chat
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllChatsUseCase @Inject constructor(private val chatRepository: ChatRepository) :
    BaseUseCase<List<Chat>, BaseUseCase.None>() {

    override suspend fun run(param: None): Result<List<Chat>> {
        TODO("Not yet implemented")
    }

    override suspend fun runFlow(param: None): Flow<Result<List<Chat>>> {
        return chatRepository.getAllChats()
    }
}
