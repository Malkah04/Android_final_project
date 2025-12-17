package com.example.finalproject_tazkartm3aj.repository.studentRep

import com.example.finalproject_tazkartm3aj.database.StudentDatabaseDao
import com.example.finalproject_tazkartm3aj.model.Student
import kotlinx.coroutines.flow.Flow

open class OfflineStudentRepository (
    private val studentDao: StudentDatabaseDao
    ) : StudentRepository {

        override fun getAllStudents(): Flow<List<Student>> = studentDao.getAllStudents()

        override fun getStudentById(id: Int): Flow<Student> = studentDao.getStudentById(id)

        override suspend fun deleteAllStudents() = studentDao.deleteAllStudents()

        override suspend fun addStudent(student: Student) = studentDao.addStudent(student)

    override suspend fun login(email: String, password: String): Student? =
        studentDao.login(email, password)


    override suspend fun getStudentByEmail(email: String): Student? =
        studentDao.getStudentByEmail(email)

    override suspend fun updateInformationOfStudent(student: Student) =
            studentDao.updateInformationOfStudent(student)

        override suspend fun deleteStudent(id: Int) = studentDao.deleteStudent(id)

    override suspend fun register(
        name: String,
        email: String,
        phone: String,
        password: String,
        year: String
    ): Boolean {
        val studentExists = studentDao.getStudentByEmail(email)

        return if (studentExists == null) {
            studentDao.addStudent(
                Student(
                    name = name,
                    email = email,
                    phone = phone,
                    password = password,
                    year = year
                )
            )
            true
        } else {
            false
        }
    }


}