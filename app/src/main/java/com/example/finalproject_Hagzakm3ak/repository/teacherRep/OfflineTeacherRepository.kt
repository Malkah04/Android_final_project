package com.example.finalproject_Hagzakm3ak.repository.teacherRep

import com.example.finalproject_Hagzakm3ak.database.TeacherDatabaseDao
import com.example.finalproject_Hagzakm3ak.model.Teacher
import kotlinx.coroutines.flow.Flow

class OfflineTeacherRepository(private val teacherDao : TeacherDatabaseDao) : TeacherRepository {
    override fun getAllTeacher(): Flow<List<Teacher>> = teacherDao.getAllTeacher()

    override fun getTeacherById(id: Int): Flow<Teacher> = teacherDao.getTeacherById(id)

    override fun deleteAllTeacher() = teacherDao.deleteAllTeacher()

    override suspend fun addTeacher(teacher: Teacher) = teacherDao.addTeacher(teacher)

    override suspend fun updateInformationOfTeacher(teacher: Teacher) =teacherDao.updateInformationOfTeacher(teacher)

    override suspend fun deleteTeacher(id: Int) = teacherDao.deleteTeacher(id)

    override fun searchByTeacherName(name:String) =teacherDao.searchByTeacherName(name)

    override suspend fun isTeacherExist(name :String , subject :String) =teacherDao.isTeacherExist(name ,subject)

}