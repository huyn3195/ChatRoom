package com.example.chatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chatapp.route.Screen
import com.example.chatapp.screen.LoginScreen
import com.example.chatapp.screen.SignUpScreen
import com.example.chatapp.ui.theme.ChatAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            ChatAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationGraph(navController)
                }
            }
        }
    }
}
@Composable
fun NavigationGraph(navController: NavHostController){
    NavHost(navController=navController, startDestination = Screen.SignUpScreen.route) {
       composable(Screen.SignUpScreen.route){
           SignUpScreen(onNavigateToLogin = {navController.navigate(Screen.LoginScreen.route)})
       }
        composable(Screen.LoginScreen.route){
            LoginScreen (onNavigateToSignUp ={navController.navigate(Screen.SignUpScreen.route)} )
        }
    }
}


