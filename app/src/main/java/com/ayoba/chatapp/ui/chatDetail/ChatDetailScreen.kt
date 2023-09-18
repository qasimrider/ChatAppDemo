package com.ayoba.chatapp.ui.chatDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ayoba.chatapp.base.UiState
import com.ayoba.chatapp.extensions.ObserveUiState
import com.ayoba.model.view.MessageView
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun ChatDetailScreen(
    chatId: Int,
    navaController: NavHostController,
    viewModel: ChatDetailViewModel = hiltViewModel()
) {

    var message by remember { mutableStateOf("") }
    val chatMessageViews = remember { mutableStateListOf<MessageView>() }


    viewModel.run {
        ObserveUiState {
            getAllMessages(chatId)
            uiState.collect { getAllMessagesUiState ->
                when (getAllMessagesUiState) {
                    is UiState.Success -> {
                        chatMessageViews.clear()
                        chatMessageViews.addAll(getAllMessagesUiState.data.messages)
//                        navController.popBackStack()
//                        chatViewList.a
                    }

                    UiState.Initialization -> {}
                    UiState.Loading -> {}
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Box(modifier = Modifier.weight(0.9f), contentAlignment = Alignment.BottomStart) {
                MessagesList(chatMessageViews)
            }

            Box(modifier = Modifier.weight(0.1f)) {
                MessageTextField(
                    message = { message },
                    onMessageChange = { message = it }, {
                        val dtf = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH)
                        val localTime = dtf.format(LocalTime.now()).toString()
                        insertMessage(message, chatId)
                        chatMessageViews.add(
                            MessageView(
                                chatId = chatId,
                                message = message,
                                timeStamp = System.currentTimeMillis() / 1000
                            )
                        )
                        message = ""
                    })
            }
        }
    }
}

@Composable
private fun MessagesList(chatMessageViews: List<MessageView>) {
    val coroutineScope = rememberCoroutineScope()
    val lazyColumnListState = rememberLazyListState()

    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        coroutineScope.launch {
            if (chatMessageViews.size > 10) {
                lazyColumnListState.scrollToItem(chatMessageViews.size - 1)
            }
        }
        items(chatMessageViews.size) { index ->

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = chatMessageViews[index].message,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.weight(0.8f)
                )

                Text(
                    text = chatMessageViews[index].timeStamp.toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.weight(0.2f)
                )
            }

            Divider()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MessageTextField(
    message: () -> String,
    onMessageChange: (String) -> Unit,
    onSendClick: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        OutlinedTextField(
            modifier = Modifier.weight(8f),
            value = message(),
            maxLines = 1,
            label = { Text(text = "Enter message here") },
            onValueChange = { onMessageChange(it) })

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            modifier = Modifier.weight(2f),
            onClick = { onSendClick() }) {
            Text(text = "Send", fontSize = 10.sp)
        }
    }
}