package com.ayoba.chatapp.ui.createChat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ayoba.chatapp.base.UiState
import com.ayoba.chatapp.extensions.ObserveUiState

@Composable
fun CreateChatScreen(
    navController: NavHostController, viewModel: ChatViewModel = hiltViewModel()
) {
    var chatName by remember { mutableStateOf("") }
    var warningDialogVisibility by remember { mutableStateOf(false) }

    viewModel.run {
        ObserveUiState {
            uiState.collect { createChatUiState ->
                when (createChatUiState) {
                    is UiState.Success -> {
                        navController.popBackStack()
                    }

                    UiState.Initialization -> {}
                    UiState.Loading -> {}
                }
            }
        }

        Card(
            modifier = Modifier
                .padding(10.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
            ) {
                ChatTextField(
                    chatName = { chatName },
                    onChatNameChange = { chatName = it })

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
//                        warningDialogVisibility = true
                        createChat(chatName)
                    }) {
                    Text(text = "Create Chat", fontSize = 10.sp)
                }
            }
            if (warningDialogVisibility) {
                CreateChatWarningDialog {
                    warningDialogVisibility = false
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChatTextField(
    chatName: () -> String,
    onChatNameChange: (String) -> Unit,
) {
    val maxChar = 50

    Row(verticalAlignment = Alignment.CenterVertically) {
        OutlinedTextField(
            value = chatName(),
            maxLines = 1,
            label = { Text(text = "Enter Chat name") },
            onValueChange = { text ->
                if (text.length <= maxChar) {
                    onChatNameChange(text)
                }
            }
        )
    }
}

@Composable
private fun CreateChatWarningDialog(onCloseDialog: () -> Unit) {
    AlertDialog(
        onDismissRequest = onCloseDialog,
        title = {
//            Header(text = "Calculation Details:", Modifier.padding(2.dp))
        },
        text = {
            Column {
                Text(
                    text = "Chat already exist",
                    fontSize = 18.sp,
                )
            }
        },
        confirmButton = {
            Button(
                onClick = onCloseDialog
            ) {
                Text(text = "OK")
            }
        }
    )
}
