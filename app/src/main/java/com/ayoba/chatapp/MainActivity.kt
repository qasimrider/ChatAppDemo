package com.ayoba.chatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.ayoba.chatapp.navigation.AyobaNavHost
import com.ayoba.chatapp.splash.SplashScreen
import com.ayoba.chatapp.ui.theme.AyobaChatAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            AyobaChatAppTheme {
            val navController = rememberNavController()
//                val appState = rememberAppState(navController)
            var displaySplashScreen by remember { mutableStateOf(true) }

            if (displaySplashScreen) {
                SplashScreen()
                LaunchedEffect(key1 = true) {
                    delay(2000)
                    displaySplashScreen = false
                }
            } else {
                AyobaChatAppTheme {
                    Scaffold(
//                        floatingActionButton = {
//                            FloatingActionButton(onClick = { navController.navigateToCreateChat() }) {
//                                Icon(
//                                    painter = painterResource(id = android.R.drawable.arrow_up_float),
//                                    contentDescription = ""
//                                )
//                            }
//                        },
//                        floatingActionButtonPosition = FabPosition.End
                    ) { innerPadding ->
                        AyobaNavHost(
                            modifier = Modifier.padding(innerPadding),
                            navController = navController,
                        )
                    }
                }
            }
        }
    }
}
//}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    AyobaChatAppTheme {
//        Greeting("Android")
//    }
//}