package com.ayoba.chatapp.ui.chatList

import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import com.ayoba.chatapp.base.BaseViewModel
import com.ayoba.chatapp.base.UiState
import com.ayoba.chatapp.base.ViewState
import com.ayoba.data.core.result.Result
import com.ayoba.domain.base.BaseUseCase
import com.ayoba.domain.useCase.GetAllChatsUseCase
import com.ayoba.model.view.ChatView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChatListViewModel @Inject constructor(val getAllChatsUseCase: GetAllChatsUseCase) :
    BaseViewModel<GetAllChatsUiState>() {

    fun getAllChats() {
        getAllChatsUseCase(
            viewModelScope = viewModelScope,
            params = BaseUseCase.None()
        ) { result ->
            viewModelScope.launch {
                when (result) {
                    is Result.Success -> {
                        _uiState.emit(UiState.Success(GetAllChatsUiState(result.data.map { it.toView() })))
                    }

                    is Result.Error -> TODO()
                    Result.Loading -> {}
                }
            }
        }
    }
}

@Immutable
data class GetAllChatsUiState(val chats: List<ChatView>) : ViewState