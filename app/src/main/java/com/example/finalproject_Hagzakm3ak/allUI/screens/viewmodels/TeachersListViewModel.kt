package com.example.finalproject_Hagzakm3ak.allUI.screens.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject_Hagzakm3ak.model.Teacher
import com.example.finalproject_Hagzakm3ak.repository.teacherRep.TeacherRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class TeachersListViewModel(
    private val repository: TeacherRepository
) : ViewModel() {

    val teachers: StateFlow<List<Teacher>> =
        repository.getAllTeacher()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
}
