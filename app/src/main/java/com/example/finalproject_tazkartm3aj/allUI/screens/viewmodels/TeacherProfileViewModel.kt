package com.example.finalproject_tazkartm3aj.allUI.screens.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.finalproject_tazkartm3aj.model.Teacher
import com.example.finalproject_tazkartm3aj.repository.teacherRep.TeacherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TeacherProfileViewModel(
    private val repo: TeacherRepository
) : ViewModel() {

    private val _teacher = MutableStateFlow<Teacher?>(null)
    val teacher: StateFlow<Teacher?> = _teacher

    fun loadTeacherById(id: Int) {
        viewModelScope.launch {
            repo.getTeacherById(id).collect {
                _teacher.value = it
            }
        }
    }

    fun updateTeacher(teacher: Teacher) {
        viewModelScope.launch {
            repo.updateInformationOfTeacher(teacher)
        }
    }

    companion object {
        fun factory(repo: TeacherRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return TeacherProfileViewModel(repo) as T
                }
            }
    }
}
