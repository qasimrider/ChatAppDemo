package com.ayoba.chatapp.ui.createChat

import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import com.ayoba.chatapp.base.BaseViewModel
import com.ayoba.chatapp.base.UiState
import com.ayoba.chatapp.base.ViewState
import com.ayoba.data.core.result.Result
import com.ayoba.domain.useCase.GetCreateChatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChatViewModel @Inject constructor(val getCreateChatUseCase: GetCreateChatUseCase) :
    BaseViewModel<CreateChatUiState>() {


    fun createChat(name: String) {

        getCreateChatUseCase(
            viewModelScope = viewModelScope,
            params = GetCreateChatUseCase.Params(name)
        ) { result ->
            viewModelScope.launch {
                when (result) {
                    is Result.Success -> {
                        _uiState.emit(UiState.Success(CreateChatUiState()))
                    }
                    is Result.Error -> TODO()
                    Result.Loading -> {}
                }
            }
        }
    }
}

@Immutable
class CreateChatUiState() : ViewState