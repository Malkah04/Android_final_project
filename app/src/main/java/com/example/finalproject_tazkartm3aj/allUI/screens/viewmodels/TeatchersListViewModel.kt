package com.example.finalproject_tazkartm3aj.allUI.screens.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject_tazkartm3aj.model.Teacher
import com.example.finalproject_tazkartm3aj.repository.teacherRep.TeacherRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class TeachersListViewModel(
    private val repository: TeacherRepository
) : ViewModel() {

    val teachers: StateFlow<List<Teacher>> =
        repository.getAllTeacher() // Flow<List<Teacher>>
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
}
