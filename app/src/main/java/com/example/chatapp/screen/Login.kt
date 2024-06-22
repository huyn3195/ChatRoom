package com.example.chatapp.screen

import android.widget.Space
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    onNavigateToSignUp:() -> Unit
){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column (
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        OutlinedTextField(value = email
            , onValueChange = {email=it},
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            label = { Text("Email") }
            )
        OutlinedTextField(value = password
            , onValueChange = {password=it},
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            visualTransformation = PasswordVisualTransformation(),
            label = { Text("Password") }
        )
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("Login", modifier = Modifier.clickable {  })
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Don't have an account? Sign up", modifier = Modifier.clickable {
            onNavigateToSignUp()
        })
    }
}
