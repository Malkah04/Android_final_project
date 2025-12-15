package com.example.finalproject_tazkartm3aj.allUI.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.finalproject_tazkartm3aj.MyApp
import com.example.finalproject_tazkartm3aj.repository.studentRep.OfflineStudentRepository
import com.example.finalproject_tazkartm3aj.repository.studentRep.StudentRepository
import kotlinx.coroutines.launch

class LoginViewModel (
    private val repository: OfflineStudentRepository
) : ViewModel() {

    var Admin by mutableStateOf(false)
    private set
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var errorMessage by mutableStateOf<String?>(null)

    fun login(
        onEmailNotFound: () -> Unit,
        onLoginSuccess: () -> Unit
    ) {
        val cleanEmail = email.trim()
        val cleanPassword = password.trim()
        errorMessage = when {
            cleanEmail.isBlank() -> "Email is required"
            !cleanEmail.contains("@") -> "Invalid email address "
            cleanPassword.isBlank() -> "Password is required"
            else -> null
        }
        if (errorMessage != null) return

        viewModelScope.launch {
          try {

              val student = repository.getStudentByEmail(cleanEmail)

              when {
                  student == null -> {
                      onEmailNotFound()
                      errorMessage ="you don't have an account"
                  }

                  student.password != cleanPassword -> {
                      errorMessage = "Wrong password"
                  }

                  else -> {
                      if(email.trim()=="admin@gmail.com"){
                          Admin = true
                      }
                      else{
                          Admin = false
                      }
                      onLoginSuccess()
                  }
              }
          }catch (e: Exception){
              Log.e("LOGIN_VM", "Error during login: ${e.message}")
              errorMessage = "Error logging in"
          }
        }
    }
    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MyApp
                val repo = OfflineStudentRepository(application.database.studentDao())
                LoginViewModel(repo)
            }
        }
    }
}