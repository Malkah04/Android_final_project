package com.example.finalproject_Hagzakm3ak.repository.studentScheduleRep

import com.example.finalproject_Hagzakm3ak.model.Schedule
import kotlinx.coroutines.flow.Flow

interface StudentScheduleRepository {
    suspend fun addLecToStudentSchedule(studentId: Int ,scheduleId:Int)

    suspend fun deleteLecFromStudentSchedule(studentId: Int ,scheduleId:Int)

    fun getStudentSchedule(studentId:Int) : Flow<List<Schedule>>
}