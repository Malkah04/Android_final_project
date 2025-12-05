package com.example.finalproject_tazkartm3aj.repository.scheduleRep

import com.example.finalproject_tazkartm3aj.model.Schedule
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {
    fun getAllSchedules(): Flow<List<Schedule>>

    fun getScheduleById(id: Int): Flow<Schedule>

    fun getSchedulesForStudent(studentId: Int): Flow<List<Schedule>>

    suspend fun deleteAllSchedules()

    suspend fun addSchedule(schedule: Schedule)

    suspend fun updateSchedule(schedule: Schedule)

    suspend fun deleteSchedule(id: Int)
}