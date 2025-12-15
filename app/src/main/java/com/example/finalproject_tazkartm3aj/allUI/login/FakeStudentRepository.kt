package com.example.finalproject_tazkartm3aj.allUI.FakeStudentRepository

import com.example.finalproject_tazkartm3aj.model.Student
import com.example.finalproject_tazkartm3aj.repository.studentRep.StudentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeStudentRepository : StudentRepository {
    private val students = mutableListOf<Student>()

    override fun getAllStudents(): Flow<List<Student>> = flowOf(students)

    override fun getStudentById(id: Int): Flow<Student> {
        val student = students.firstOrNull() ?: Student(0, "", "", "", "", year = "")
        return flowOf(student)
    }

    override suspend fun deleteAllStudents() {
        students.clear()
    }

    override suspend fun addStudent(student: Student) {
        students.add(student)
    }

    override suspend fun updateInformationOfStudent(student: Student) {
        val index = students.indexOfFirst { it.email == student.email }
        if (index != -1) students[index] = student
    }

    override suspend fun deleteStudent(id: Int) {
    }

    override suspend fun register(
        name: String,
        email: String,
        phone: String,
        password: String,
        year: String
    ): Boolean {
        val exists = students.any { it.email == email }
        if (exists) return false
        students.add(Student(name = name, email = email, phone = phone, password = password, year = year))
        return true
    }

    override suspend fun login(email: String, password: String): Student? {
        return students.find { it.email == email && it.password == password }
    }

    override suspend fun getStudentByEmail(email: String): Student? {
        return students.find { it.email == email }
    }
}
