package com.example.finalproject_tazkartm3aj.allUI.screens.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject_tazkartm3aj.model.Student
import com.example.finalproject_tazkartm3aj.repository.studentRep.StudentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class StudentProfileViewModel(
    private val repo: StudentRepository,
    private val studentId: Int
) : ViewModel() {

    private val _student = MutableStateFlow<Student?>(null)
    val student: StateFlow<Student?> get() = _student

    init {
        viewModelScope.launch {
            repo.getStudentById(studentId).collectLatest {
                _student.value = it
            }
        }
    }
}
