package com.example.finalproject_tazkartm3aj.allUI.screens.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.finalproject_tazkartm3aj.repository.teacherRep.TeacherRepository

class TeacherVMFactory(
    private val repo: TeacherRepository,
    private val teacherId: Int
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TeacherProfileViewModel(repo, teacherId) as T
    }
}
