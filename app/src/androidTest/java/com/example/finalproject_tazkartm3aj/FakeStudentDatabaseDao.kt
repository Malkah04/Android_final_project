package com.example.finalproject_tazkartm3aj

import com.example.finalproject_tazkartm3aj.database.StudentDatabaseDao
import com.example.finalproject_tazkartm3aj.model.Student
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeStudentDatabaseDao : StudentDatabaseDao {

    private val students = mutableListOf<Student>(
        Student(
            _id = 1,
            name = "Test User",
            phone = "0100000000",
            email = "test@test.com",
            password = "1234",
            year = "2025",
            imagePath = null
        ),
        Student(
            _id = 2,
            name = "Admin User",
            phone = "0101111111",
            email = "admin@gmail.com",
            password = "admin",
            year = "2025",
            imagePath = null
        )
    )

    override fun getAllStudents(): Flow<List<Student>> = flow { emit(students) }

    override fun getStudentById(id: Int): Flow<Student> = flow {
        emit(students.first { it._id == id })
    }

    override suspend fun deleteAllStudents() {
        students.clear()
    }

    override suspend fun addStudent(student: Student) {
        val nextId = (students.maxOfOrNull { it._id } ?: 0) + 1
        val s = if (student._id == 0) student.copy(_id = nextId) else student
        students.add(s)
    }

    override suspend fun updateInformationOfStudent(student: Student) {
        val index = students.indexOfFirst { it._id == student._id }
        if (index != -1) students[index] = student
    }

    override suspend fun deleteStudent(id: Int) {
        students.removeIf { it._id == id }
    }

    override suspend fun login(email: String, password: String): Student? {
        return students.find { it.email == email && it.password == password }
    }

    override suspend fun getStudentByEmail(email: String): Student? {
        return students.find { it.email == email }
    }
}