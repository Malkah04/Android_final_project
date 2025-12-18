package com.example.finalproject_Hagzakm3ak.repository.studentRep
import com.example.finalproject_Hagzakm3ak.model.Student
import kotlinx.coroutines.flow.Flow


interface StudentRepository {
    fun getAllStudents(): Flow<List<Student>>

    fun getStudentById(id: Int): Flow<Student>

    suspend fun deleteAllStudents()

    suspend fun addStudent(student: Student)

    suspend fun updateInformationOfStudent(student: Student)

    suspend fun deleteStudent(id: Int)

    suspend fun register(name: String, email: String, phone: String, password: String,year: String): Boolean
    suspend fun login(email: String, password: String): Student?
    suspend fun getStudentByEmail(email: String): Student?

}