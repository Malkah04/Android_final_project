package com.example.finalproject_Hagzakm3ak.repository.scheduleRep

import com.example.finalproject_Hagzakm3ak.model.Schedule
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {
    fun getAllSchedules(): Flow<List<Schedule>>

    fun getScheduleById(id: Int): Flow<Schedule>

    suspend fun deleteAllSchedules()

    suspend fun addSchedule(schedule: Schedule)

    suspend fun updateSchedule(schedule: Schedule)

    suspend fun deleteSchedule(id: Int)

    fun SearchByCenterNameInSchedule(centerName:String) : Flow<List<Schedule>>
    fun SearchByLocationInSchedule(location :String) :Flow<List<Schedule>>
    fun SearchBySubjectInSchedule(subjectName:String) : Flow<List<Schedule>>
    fun SearchByTeacherNameInSchedule(teacherName:String): Flow<List<Schedule>>
}