package com.example.chatapp.route

sealed class Screen(val route: String) {
    object LoginScreen: Screen("loginscreen")
    object SignUpScreen: Screen("signupscreen")
    object ChatScreen: Screen("chatscreen")
    object ChatRoomListScreen: Screen("chatroomlistscreen")
}