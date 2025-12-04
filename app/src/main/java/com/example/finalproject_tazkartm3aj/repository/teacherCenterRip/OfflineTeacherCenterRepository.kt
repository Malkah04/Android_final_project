package com.example.finalproject_tazkartm3aj.repository.teacherCenterRip

import com.example.finalproject_tazkartm3aj.database.TeacherCenterDataBaseDao
import com.example.finalproject_tazkartm3aj.model.CenterTeacherCrossRef
import com.example.finalproject_tazkartm3aj.repository.teacherCenterRip.TeacherCenterRepository
import kotlinx.coroutines.flow.Flow
import com.example.finalproject_tazkartm3aj.model.TeacherOfCenter
import com.example.finalproject_tazkartm3aj.model.CentersOfOneTeacher


class OfflineTeacherCenterRepository(private val dao: TeacherCenterDataBaseDao):
    TeacherCenterRepository {
    override suspend fun addCenterTeacher(centerId: Int, teacherId: Int) =dao.addTeacherCenter(
        CenterTeacherCrossRef(centerId = centerId, teacherId = teacherId)
    )


    override suspend fun removeCenterTeacher(centerId: Int, teacherId: Int) =dao.deleteTeacherCenter(
        CenterTeacherCrossRef(centerId = centerId, teacherId = teacherId)
    )


    override fun getAllTeacherOfCenter(centerId: Int): Flow<TeacherOfCenter> =dao.getAllTeacherOfCenter(centerId)

    override fun getCenterOfTeacher(teacherId: Int): Flow<CentersOfOneTeacher> =dao.getCenterOfTeacher(teacherId)

}