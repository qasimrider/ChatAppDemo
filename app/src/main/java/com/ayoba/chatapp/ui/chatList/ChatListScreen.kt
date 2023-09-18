package com.ayoba.chatapp.ui.chatList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ayoba.chatapp.base.UiState
import com.ayoba.chatapp.extensions.ObserveUiState
import com.ayoba.chatapp.navigation.chat.navigateToChatDetail
import com.ayoba.chatapp.navigation.chat.navigateToCreateChat
import com.ayoba.model.view.ChatView

@Composable
fun ChatListScreen(
    navController: NavHostController,
    viewModel: ChatListViewModel = hiltViewModel()
) {

    val chats = remember { mutableStateListOf<ChatView>() }

    viewModel.run {
        ObserveUiState {
            getAllChats()
            uiState.collect { getAllChatsUiState ->
                when (getAllChatsUiState) {
                    is UiState.Success -> {
                        chats.clear()
                        chats.addAll(getAllChatsUiState.data.chats)
//                        navController.popBackStack()
//                        chatViewList.a
                    }

                    UiState.Initialization -> {}
                    UiState.Loading -> {}
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
    ) {
        if (chats.isEmpty()) {
            Text(
                text = "Create your first chat",
                fontSize = 24.sp,
                color = Color.Red,
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            ChatList(chatViewList = chats) { chat -> navController.navigateToChatDetail(chat.id) }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            FloatingActionButton(
                modifier = Modifier.align(Alignment.BottomEnd),
                onClick = { navController.navigateToCreateChat() }) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = ""
                )
            }
        }
    }
}

@Composable
private fun ChatList(chatViewList: List<ChatView>, onChatClick: (ChatView) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
    ) {
        items(chatViewList.size) { index ->
            ChatCard(chatViewList[index]) { onChatClick(it) }
        }
    }
}

@Composable
private fun ChatCard(chatView: ChatView, onChatClick: (ChatView) -> Unit) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
    ) {
        Column(
            Modifier
                .padding(8.dp)
                .clickable { onChatClick(chatView) }) {
            Text(text = chatView.name, fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
            Row(
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
//                    text = chatView.messages.last().message,
                    text = "Dummy Message",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
//                    text = chatView.messages.last().time,
                    text = "Dummy time",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}