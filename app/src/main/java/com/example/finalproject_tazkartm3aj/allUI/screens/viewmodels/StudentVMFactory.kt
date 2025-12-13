package com.example.finalproject_tazkartm3aj.allUI.screens.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.finalproject_tazkartm3aj.repository.studentRep.StudentRepository


class StudentVMFactory(
    private val repo: StudentRepository,
    private val studentId: Int
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StudentProfileViewModel(repo, studentId) as T
    }
}
