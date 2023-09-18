package com.ayoba.chatapp.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.ayoba.chatapp.navigation.chat.chatDetailNavigation
import com.ayoba.chatapp.navigation.chat.chatListNavigation
import com.ayoba.chatapp.navigation.chat.chatListRoute
import com.ayoba.chatapp.navigation.chat.createChatNavigation


@Composable
fun AyobaNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = chatListRoute,
    ) {
        chatListNavigation(navController)
        chatDetailNavigation(navController)
        createChatNavigation(navController)

    }
}