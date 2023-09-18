package com.ayoba.chatapp.navigation.chat

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ayoba.chatapp.ui.chatList.ChatListScreen

const val chatListRoute = "chatRoute"

fun NavController.navigateToChatList(navOptions: NavOptions? = null) {
    this.navigate(chatListRoute, navOptions)
}

fun NavGraphBuilder.chatListNavigation(navController: NavHostController) {
    composable(route = chatListRoute) {
        ChatListScreen(navController)
    }
}