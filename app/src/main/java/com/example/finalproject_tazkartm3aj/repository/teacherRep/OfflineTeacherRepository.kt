package com.example.finalproject_tazkartm3aj.repository.teacherRep

import com.example.finalproject_tazkartm3aj.database.TeacherDatabaseDao
import com.example.finalproject_tazkartm3aj.model.Teacher
import com.example.finalproject_tazkartm3aj.repository.teacherRep.TeacherRepository
import kotlinx.coroutines.flow.Flow

class OfflineTeacherRepository(private val teacherDao : TeacherDatabaseDao) : TeacherRepository {
    override fun getAllTeacher(): Flow<List<Teacher>> = teacherDao.getAllTeacher()

    override fun getTeacherById(id: Int): Flow<Teacher> = teacherDao.getTeacherById(id)

    override fun deleteAllTeacher() = teacherDao.deleteAllTeacher()

    override suspend fun addTeacher(teacher: Teacher) = teacherDao.addTeacher(teacher)

    override suspend fun updateInformationOfTeacher(teacher: Teacher) =teacherDao.updateInformationOfTeacher(teacher)

    override suspend fun deleteTeacher(id: Int) = teacherDao.deleteTeacher(id)

    override fun searchByTeacherName(name:String) =teacherDao.searchByTeacherName(name)

}