package com.ayoba.model.data

import com.ayoba.model.view.ChatView
import com.ayoba.model.view.MessageView

class Chat(val id: Int = 0, val name: String, val messages: List<Message> = listOf()) {
    fun toView() = ChatView(id = id, name = name, messages = messages.map { it.toView() })

    class Message(
        val id: Int = 0,
        val chatId: Int,
        val message: String,
        val timeStamp: Long = 9L
    ) {
        fun toView() =
            MessageView(chatId = chatId, id = id, message = message, timeStamp = timeStamp)
    }
}
