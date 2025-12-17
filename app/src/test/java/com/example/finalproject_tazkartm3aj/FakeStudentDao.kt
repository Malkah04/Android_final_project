package com.example.finalproject_tazkartm3aj

import com.example.finalproject_tazkartm3aj.database.StudentDatabaseDao
import com.example.finalproject_tazkartm3aj.model.Student
import kotlinx.coroutines.flow.Flow

class FakeStudentDao : StudentDatabaseDao {

    private var student: Student? = null

    override suspend fun getStudentByEmail(email: String): Student? {
        return student
    }

    override suspend fun login(
        email: String,
        password: String
    ): Student? {
        TODO("Not yet implemented")
    }

    override fun getAllStudents(): Flow<List<Student>> {
        TODO("Not yet implemented")
    }

    override fun getStudentById(id: Int): Flow<Student> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllStudents() {
        TODO("Not yet implemented")
    }

    override suspend fun addStudent(student: Student) {
        TODO("Not yet implemented")
    }

    override suspend fun updateInformationOfStudent(student: Student) {
        this.student = student
    }

    override suspend fun deleteStudent(id: Int) {
        TODO("Not yet implemented")
    }
}