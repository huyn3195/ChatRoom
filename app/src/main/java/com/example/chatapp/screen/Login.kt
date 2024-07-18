package com.example.chatapp.screen

import android.widget.Space
import com.example.chatapp.data.Result
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chatapp.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    onNavigateToSignUp:() -> Unit,
    onSignInSuccess:() -> Unit
){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val result by authViewModel.authResult.observeAsState()
    val focusManager = LocalFocusManager.current
    Column (
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)
            .clickable(onClick = {focusManager.clearFocus()})
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        OutlinedTextField(value = email
            , onValueChange = {email=it},
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)

            ,
            label = { Text("Email") }
            )
        OutlinedTextField(value = password
            , onValueChange = {password=it},
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            visualTransformation = PasswordVisualTransformation(),
            label = { Text("Password") }
        )
        Button(
            onClick = {
                authViewModel.logIn(email, password)
                when(result){
                    is Result.Success -> {
                        onSignInSuccess()
                    }
                    is Result.Error -> {

                    }
                    else ->{

                    }
                }
            },
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

