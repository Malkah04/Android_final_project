package com.example.finalproject_tazkartm3aj.repository.scheduleRep

import com.example.finalproject_tazkartm3aj.database.ScheduleDatabaseDao
import com.example.finalproject_tazkartm3aj.model.Schedule
import kotlinx.coroutines.flow.Flow

class OfflineScheduleRepository (
    private val scheduleDao: ScheduleDatabaseDao
):ScheduleRepository {

    override fun getAllSchedules(): Flow<List<Schedule>> = scheduleDao.getAllSchedules()

    override fun getScheduleById(id: Int): Flow<Schedule> = scheduleDao.getScheduleById(id)

    override fun getSchedulesForStudent(studentId: Int): Flow<List<Schedule>> =
        scheduleDao.getSchedulesForStudent(studentId)

    override suspend fun deleteAllSchedules() = scheduleDao.deleteAllSchedules()

    override suspend fun addSchedule(schedule: Schedule) = scheduleDao.addSchedule(schedule)

    override suspend fun updateSchedule(schedule: Schedule) =
        scheduleDao.updateSchedule(schedule)

    override suspend fun deleteSchedule(id: Int) = scheduleDao.deleteSchedule(id)
}