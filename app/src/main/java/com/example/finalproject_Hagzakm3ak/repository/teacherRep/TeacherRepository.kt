package com.example.finalproject_Hagzakm3ak.repository.teacherRep

import com.example.finalproject_Hagzakm3ak.model.Teacher
import kotlinx.coroutines.flow.Flow

interface TeacherRepository {

    fun getAllTeacher(): Flow<List<Teacher>>

    fun getTeacherById(id:Int) : Flow<Teacher?>

    fun deleteAllTeacher()

    suspend fun addTeacher(teacher: Teacher)

    suspend fun updateInformationOfTeacher(teacher: Teacher)

    suspend fun deleteTeacher(id:Int)

    fun searchByTeacherName(name:String) : Flow<List<Teacher>>

    suspend fun isTeacherExist(name :String , subject :String) : Boolean
}