package com.example.finalproject_Hagzakm3ak.allUI.addTeacher

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.finalproject_Hagzakm3ak.MyApp
import com.example.finalproject_Hagzakm3ak.allUI.addCenter.checkPhoneNumber
import com.example.finalproject_Hagzakm3ak.database.TeacherDatabaseDao
import com.example.finalproject_Hagzakm3ak.model.Teacher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.lang.Exception

class AddTeacherVM(private val teacherDatabaseDao: TeacherDatabaseDao): ViewModel() {

    init {
        viewModelScope.launch {
            try {
                val centersList = teacherDatabaseDao.getAllTeacher().first()

                if (centersList.isEmpty()) {
                    Log.d("DB_DEBUG", "No Teacher found in the database.")
                } else {
                    centersList.forEach {
                        Log.d("DB_DEBUG", "Teacher: ${it._id} ${it.name}, ${it.subject}, ${it.phone}")
                    }
                }
            } catch (e: kotlin.Exception) {
                Log.e("DB_DEBUG", "Error reading centers from Flow: ${e.message}")
            }
        }
    }
    val state = mutableStateOf("")
    val isProcessing =mutableStateOf(false)


    fun addTeacher(teacher: Teacher){
        viewModelScope.launch {
            try {
                teacherDatabaseDao.addTeacher(teacher)
                state.value ="success"
            }catch (e: Exception){
                state.value ="fail"
            }
        }
    }
    fun resetState(){
        state.value=""
    }

    suspend fun teacherExist(name: String ,subject: String) : Boolean{
        return teacherDatabaseDao.isTeacherExist(name,subject)
    }

    fun addTeacherIfValid(name: String, subject: String, phone: String) {
        viewModelScope.launch {
            isProcessing.value = true

            val phoneValid = checkPhoneNumber(phone) == "ok"
            val teacherExit = teacherExist(name, subject)

            if (!phoneValid) {
                state.value = "Invalid phone number"
            } else if (teacherExit) {
                state.value = "Center already exists!"
            } else {
                addTeacher(Teacher(name = name, subject = subject, phone = phone))
            }

            isProcessing.value = false
        }
    }


    companion object{
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =this[APPLICATION_KEY]
                val app = application as MyApp
                AddTeacherVM(app.database.teacherDao())
            }
        }
    }
}