package com.example.finalproject_tazkartm3aj.allUI.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var errorMessage by mutableStateOf<String?>(null)

    fun login() {
        errorMessage = when {
            email.isBlank() -> "Email is required"
            !email.contains("@") -> "Invalid email address "
            password.length < 6 -> "Password too short"
            else -> null
        }
    }
}