package com.ayoba.chatapp.navigation.chat

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ayoba.chatapp.ui.chatDetail.ChatDetailScreen

const val chatDetailRoute = "chatDetailRoute"
internal const val chatIdArg = "chatId"

fun NavController.navigateToChatDetail(chatId: Int) {
    this.navigate(route = "${chatDetailRoute}/$chatId")
}

fun NavGraphBuilder.chatDetailNavigation(navController: NavHostController) {
    composable(
        route = chatDetailRoute.plus("/{$chatIdArg}"),
        arguments = listOf(navArgument(chatIdArg) { type = NavType.IntType })
    ) { navBackStackEntry ->
        val chatId = navBackStackEntry.arguments?.getInt(chatIdArg)!!
        ChatDetailScreen(chatId, navController)
    }
}
