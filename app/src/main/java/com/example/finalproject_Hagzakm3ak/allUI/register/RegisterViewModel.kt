package com.example.finalproject_Hagzakm3ak.allUI.register

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
import com.example.finalproject_Hagzakm3ak.MyApp
import com.example.finalproject_Hagzakm3ak.allUI.addCenter.checkPhoneNumber
import com.example.finalproject_Hagzakm3ak.model.Student
import com.example.finalproject_Hagzakm3ak.repository.studentRep.OfflineStudentRepository
import com.example.finalproject_Hagzakm3ak.repository.studentRep.StudentRepository
import kotlinx.coroutines.launch

class RegisterViewModel (
    private val repository: StudentRepository
) : ViewModel() {

    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var phone by mutableStateOf("")
    var year by mutableStateOf("")
    var errorMessage by mutableStateOf<String?>(null)

    var registrationSuccess by mutableStateOf(false)
    fun register(onSuccess: () -> Unit) {
        Log.d("RegisterVM", "Register button clicked")
        errorMessage = when {
            name.isBlank() -> "Name required" //empty
            email.isBlank() -> "Email required"
            !email.contains("@") -> "Invalid email address "
            password.length < 6 -> "Password must be 6+ chars"
            checkPhoneNumber(phone) !="ok" -> "Phone must have 11 digits"
            year.isBlank() -> "Year required"
            else -> null
        }
        if (errorMessage != null) return

        viewModelScope.launch {
            val cleanEmail = email.trim()
            Log.d("RegisterVM", "Checking email: $cleanEmail")
            try{
                val existingStudent = repository.getStudentByEmail(cleanEmail)
                if (existingStudent != null) {
                    errorMessage = "Email already exists"
                    return@launch
                }
                Log.d("RegisterVM", "Email not found, inserting student")

                val student = Student(name = name , phone = phone , password = password , year = year , email = email)
                repository.addStudent(student)
                Log.d("RegisterVM", "Student inserted successfully")
                registrationSuccess = true
                onSuccess()



            }catch (e: Exception){
                Log.e("RegisterVM", "Error during registration", e)
                errorMessage ="error"

            }

        }
    }
    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MyApp
                val repo = OfflineStudentRepository(application.database.studentDao())
                RegisterViewModel(repo)
            }
        }
    }

}