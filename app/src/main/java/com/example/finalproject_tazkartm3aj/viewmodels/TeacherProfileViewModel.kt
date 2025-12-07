package com.example.finalproject_tazkartm3aj.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject_tazkartm3aj.model.Teacher
import com.example.finalproject_tazkartm3aj.repository.teacherRep.TeacherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TeacherProfileViewModel(
    private val repo: TeacherRepository,
    private val teacherId: Int
) : ViewModel() {

    private val _teacher = MutableStateFlow<Teacher?>(null)
    val teacher: StateFlow<Teacher?> get() = _teacher

    init {
        viewModelScope.launch {
            repo.getTeacherById(teacherId).collectLatest {
                _teacher.value = it
            }
        }
    }
}
