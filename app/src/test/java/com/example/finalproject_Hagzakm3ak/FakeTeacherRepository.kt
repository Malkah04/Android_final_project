package com.example.finalproject_Hagzakm3ak

import com.example.finalproject_Hagzakm3ak.model.Teacher
import com.example.finalproject_Hagzakm3ak.repository.teacherRep.TeacherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeTeacherRepository : TeacherRepository {

    private val teacherFlow = MutableStateFlow<Teacher?>(null)
    override fun getAllTeacher(): Flow<List<Teacher>> {
        TODO("Not yet implemented")
    }

    override fun getTeacherById(id: Int): Flow<Teacher?> {
        return teacherFlow
    }

    override fun deleteAllTeacher() {
        TODO("Not yet implemented")
    }

    override suspend fun addTeacher(teacher: Teacher) {
        TODO("Not yet implemented")
    }

    override suspend fun updateInformationOfTeacher(teacher: Teacher) {
        teacherFlow.emit(teacher)
    }
    override suspend fun deleteTeacher(id: Int) {
        TODO("Not yet implemented")
    }

    override fun searchByTeacherName(name: String): Flow<List<Teacher>> {
        TODO("Not yet implemented")
    }

    override suspend fun isTeacherExist(
        name: String,
        subject: String
    ): Boolean {
        TODO("Not yet implemented")
    }
}

