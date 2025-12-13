package com.example.finalproject_tazkartm3aj.allUI.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class RegisterViewModel : ViewModel() {

    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var errorMessage by mutableStateOf<String?>(null)

    fun register() {
        errorMessage = when {
            name.isBlank() -> "Name required" //empty
            email.isBlank() -> "Email required"
            !email.contains("@") -> "Invalid email address "
            password.length < 6 -> "Password must be 6+ chars"
            else -> null
        }
    }
}