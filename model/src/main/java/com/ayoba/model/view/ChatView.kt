package com.ayoba.model.view

import androidx.compose.runtime.Stable

@Stable
data class ChatView(val id: Int, val name: String, val messages: List<MessageView>)

@Stable
data class MessageView(
    val id: Int = 0,
    val chatId: Int,
    val message: String,
    val timeStamp: Long
)