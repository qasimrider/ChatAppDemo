package com.ayoba.chatapp.ui.chatDetail

import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import com.ayoba.chatapp.base.BaseViewModel
import com.ayoba.chatapp.base.UiState
import com.ayoba.chatapp.base.ViewState
import com.ayoba.data.core.result.Result
import com.ayoba.domain.useCase.GetAllMessagesUseCase
import com.ayoba.domain.useCase.GetInsertMessagesUseCase
import com.ayoba.model.view.MessageView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChatDetailViewModel @Inject constructor(
    val getInsertMessagesUseCase: GetInsertMessagesUseCase,
    val getAllMessagesUseCase: GetAllMessagesUseCase
) :
    BaseViewModel<ChatDetailUiState>() {

    fun insertMessage(message: String, chatId: Int) {
        getInsertMessagesUseCase(
            viewModelScope = viewModelScope,
            params = GetInsertMessagesUseCase.Params(
                message = message,
                chatId = chatId,
                timeStamp = System.currentTimeMillis() / 1000
            )
        ) { result ->
            viewModelScope.launch {
                when (result) {
                    is Result.Success -> {
                        _uiState.emit(UiState.Success(ChatDetailUiState()))
                    }

                    is Result.Error -> TODO()
                    Result.Loading -> {}
                }
            }
        }
    }

    fun getAllMessages(chatId: Int) {
        getAllMessagesUseCase(
            viewModelScope = viewModelScope,
            params = GetAllMessagesUseCase.Params(chatId)
        ) { result ->
            viewModelScope.launch {
                when (result) {
                    is Result.Success -> {
                        _uiState.emit(UiState.Success(ChatDetailUiState(messages = result.data.map { it.toView() })))
                    }

                    is Result.Error -> TODO()
                    Result.Loading -> {}
                }
            }
        }
    }
}

@Immutable
class ChatDetailUiState(val inserted: Boolean = false, val messages: List<MessageView> = listOf()) :
    ViewState