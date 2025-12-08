package com.example.finalproject_tazkartm3aj.allUI.login

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoginScreen(
    onRegisterClick: () -> Unit,
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    val email = viewModel.email
    val password = viewModel.password
    val error = viewModel.errorMessage

    Column(
        modifier = Modifier.Companion
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text("Login", style = MaterialTheme.typography.headlineMedium, color = Color(0xFF003366))

        Spacer(Modifier.Companion.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { viewModel.email = it },
            label = { Text("Email") },
            modifier = Modifier.Companion.fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = { viewModel.password = it },
            label = { Text("Password") },
            modifier = Modifier.Companion.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        error?.let {
            Text(
                text = it,
                color = Color.Companion.Red,
                modifier = Modifier.Companion.padding(top = 8.dp)
            )
        }

        Spacer(Modifier.Companion.height(16.dp))

        Button(
            onClick = {
                viewModel.login()
                if (viewModel.errorMessage == null) onLoginSuccess()
            },
            modifier = Modifier.Companion.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color(0xFF003366)
            )

        ) {
            Text("Login", fontSize = 17.sp)
        }

        TextButton(onClick = onRegisterClick,

            ) {
            Text(text = "Don’t have an account? Register" ,
                color = Color(0xFFF1970E),
                letterSpacing=1.5.sp,
                fontSize = 14.sp


            )
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable") //to can use LoginViewModel
@Preview(showBackground = true)
@Composable
fun  PreviewLogin () {

    LoginScreen({ },{}, LoginViewModel())

}