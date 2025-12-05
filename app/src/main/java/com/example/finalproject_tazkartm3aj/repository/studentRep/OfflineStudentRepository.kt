package com.example.finalproject_tazkartm3aj.repository.studentRep

import com.example.finalproject_tazkartm3aj.database.StudentDatabaseDao
import com.example.finalproject_tazkartm3aj.model.Student
import kotlinx.coroutines.flow.Flow

class OfflineStudentRepository (
    private val studentDao: StudentDatabaseDao
    ) : StudentRepository {

        override fun getAllStudents(): Flow<List<Student>> = studentDao.getAllStudents()

        override fun getStudentById(id: Int): Flow<Student> = studentDao.getStudentById(id)

        override suspend fun deleteAllStudents() = studentDao.deleteAllStudents()

        override suspend fun addStudent(student: Student) = studentDao.addStudent(student)

        override suspend fun updateInformationOfStudent(student: Student) =
            studentDao.updateInformationOfStudent(student)

        override suspend fun deleteStudent(id: Int) = studentDao.deleteStudent(id)

}