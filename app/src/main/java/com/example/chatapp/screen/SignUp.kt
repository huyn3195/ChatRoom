package com.example.chatapp.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.chatapp.viewmodel.AuthViewModel


@Composable
fun SignUpScreen(
    authViewModel: AuthViewModel,
    onNavigateToLogin:() ->Unit
){
    var email by remember { mutableStateOf("")}
    var password by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    Column (
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)
            .clickable(onClick = {focusManager.clearFocus()})
            ,

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
    ){
        OutlinedTextField(value = email,
            onValueChange = {email = it},
            label = {Text("Email")},
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
            )
        OutlinedTextField(value = password, onValueChange = {password=it},
                label = {Text("Password")},
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                visualTransformation = PasswordVisualTransformation()
            )
        OutlinedTextField(value = firstName,
            onValueChange = {firstName = it},
            label = {Text("First Name:")},
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
        )
        OutlinedTextField(value = lastName, onValueChange = {lastName=it},
            label = {Text("Last Name:")},
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        Button(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp),
            onClick = {
                authViewModel.signUp(email, password, firstName, lastName)
                email = ""
                password = ""
                firstName = ""
                lastName = ""
            }) {
            Text(text = "Sign Up",
                modifier = Modifier.padding(8.dp)
            )

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Already have an account? Sign in",
            modifier = Modifier.clickable {
                onNavigateToLogin()
            }
        )
    }
}

