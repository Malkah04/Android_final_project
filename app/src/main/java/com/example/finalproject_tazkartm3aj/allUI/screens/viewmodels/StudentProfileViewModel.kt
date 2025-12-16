package com.example.finalproject_tazkartm3aj.allUI.screens.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.finalproject_tazkartm3aj.MyApp
import com.example.finalproject_tazkartm3aj.model.Student
import com.example.finalproject_tazkartm3aj.database.StudentDatabaseDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY

class StudentProfileViewModel(
    private val dao: StudentDatabaseDao
) : ViewModel() {

    private val _student = MutableStateFlow<Student?>(null)
    val student: StateFlow<Student?> = _student

    fun loadStudentByEmail(email: String) {
        viewModelScope.launch {
            _student.value = dao.getStudentByEmail(email)
        }
    }

    fun updateStudent(student: Student) {
        viewModelScope.launch {
            dao.updateInformationOfStudent(student)
            _student.value = student 
        }
    }

    companion object {
        fun factory(): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as MyApp
                StudentProfileViewModel(app.database.studentDao())
            }
        }
    }
}
