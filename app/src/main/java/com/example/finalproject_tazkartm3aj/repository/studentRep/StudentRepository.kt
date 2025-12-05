package com.example.finalproject_tazkartm3aj.repository.studentRep
import com.example.finalproject_tazkartm3aj.model.Student
import kotlinx.coroutines.flow.Flow


interface StudentRepository {
    fun getAllStudents(): Flow<List<Student>>

    fun getStudentById(id: Int): Flow<Student>

    suspend fun deleteAllStudents()

    suspend fun addStudent(student: Student)

    suspend fun updateInformationOfStudent(student: Student)

    suspend fun deleteStudent(id: Int)
}