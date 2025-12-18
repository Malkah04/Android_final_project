package com.example.finalproject_Hagzakm3ak.repository.teacherCenterRip

import kotlinx.coroutines.flow.Flow
import com.example.finalproject_Hagzakm3ak.model.TeacherOfCenter
import com.example.finalproject_Hagzakm3ak.model.CentersOfOneTeacher

interface TeacherCenterRepository {

    suspend fun addCenterTeacher(centerId :Int ,teacherId : Int)

    suspend fun removeCenterTeacher(centerId :Int ,teacherId: Int)

    fun getAllTeacherOfCenter(centerId :Int) : Flow<TeacherOfCenter>

    fun getCenterOfTeacher(teacherId :Int) : Flow<CentersOfOneTeacher>

}