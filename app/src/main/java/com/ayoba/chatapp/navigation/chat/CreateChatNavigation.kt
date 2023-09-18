package com.ayoba.chatapp.navigation.chat

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ayoba.chatapp.ui.createChat.CreateChatScreen

const val createChatRoute = "createChatRoute"

fun NavController.navigateToCreateChat(navOptions: NavOptions? = null) {
    this.navigate(createChatRoute, navOptions)
}

fun NavGraphBuilder.createChatNavigation(navController: NavHostController) {
    composable(route = createChatRoute) {
        CreateChatScreen(navController)
    }
}