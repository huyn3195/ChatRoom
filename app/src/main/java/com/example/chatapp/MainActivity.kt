package com.example.chatapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chatapp.route.Screen
import com.example.chatapp.screen.ChatRoomListScreen
import com.example.chatapp.screen.ChatScreen
import com.example.chatapp.screen.LoginScreen
import com.example.chatapp.screen.SignUpScreen
import com.example.chatapp.ui.theme.ChatAppTheme
import com.example.chatapp.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val authViewModel: AuthViewModel = viewModel()
            ChatAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationGraph(navController,authViewModel)
                }
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel
){
    NavHost(navController=navController, startDestination = Screen.SignUpScreen.route) {
       composable(Screen.SignUpScreen.route){
           SignUpScreen(authViewModel= authViewModel,
               onNavigateToLogin = {navController.navigate(Screen.LoginScreen.route)})
       }
        composable(Screen.LoginScreen.route) {
            LoginScreen(
                authViewModel = authViewModel,
                onNavigateToSignUp = { navController.navigate(Screen.SignUpScreen.route) }
            ) {
                navController.navigate(Screen.ChatRoomListScreen.route)
            }
        }
        composable(Screen.ChatRoomListScreen.route){
            ChatRoomListScreen{
                navController.navigate("${Screen.ChatScreen.route}/${it.id}")
            }
        }
        composable("${Screen.ChatScreen.route}/{roomId}"){
            val roomId: String = it
                .arguments?.getString("roomId") ?: ""
            ChatScreen(roomId= roomId)
            }
        }
    }



