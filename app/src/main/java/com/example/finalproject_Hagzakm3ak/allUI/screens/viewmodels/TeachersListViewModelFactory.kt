package com.example.finalproject_Hagzakm3ak.allUI.screens.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.finalproject_Hagzakm3ak.repository.teacherRep.TeacherRepository

class TeachersListViewModelFactory(
    private val repository: TeacherRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TeachersListViewModel::class.java)) {
            return TeachersListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}