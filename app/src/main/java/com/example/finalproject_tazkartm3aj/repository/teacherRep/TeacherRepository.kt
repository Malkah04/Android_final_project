package com.example.finalproject_tazkartm3aj.repository.teacherRep

import com.example.finalproject_tazkartm3aj.model.Teacher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

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