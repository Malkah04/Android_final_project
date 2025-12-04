package com.example.finalproject_tazkartm3aj.repository.teacherCenterRip

import kotlinx.coroutines.flow.Flow
import com.example.finalproject_tazkartm3aj.model.TeacherOfCenter
import com.example.finalproject_tazkartm3aj.model.CentersOfOneTeacher

interface TeacherCenterRepository {

    suspend fun addCenterTeacher(centerId :Int ,teacherId : Int)

    suspend fun removeCenterTeacher(centerId :Int ,teacherId: Int)

    fun getAllTeacherOfCenter(centerId :Int) : Flow<TeacherOfCenter>

    fun getCenterOfTeacher(teacherId :Int) : Flow<CentersOfOneTeacher>

}