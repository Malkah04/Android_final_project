package com.example.finalproject_Hagzakm3ak.repository.scheduleRep

import com.example.finalproject_Hagzakm3ak.database.ScheduleDatabaseDao
import com.example.finalproject_Hagzakm3ak.model.Schedule
import kotlinx.coroutines.flow.Flow

class OfflineScheduleRepository (
    private val scheduleDao: ScheduleDatabaseDao
):ScheduleRepository {

    override fun getAllSchedules(): Flow<List<Schedule>> = scheduleDao.getAllSchedules()

    override fun getScheduleById(id: Int): Flow<Schedule> = scheduleDao.getScheduleById(id)

    override suspend fun deleteAllSchedules() = scheduleDao.deleteAllSchedules()

    override suspend fun addSchedule(schedule: Schedule) = scheduleDao.addSchedule(schedule)

    override suspend fun updateSchedule(schedule: Schedule) =
        scheduleDao.updateSchedule(schedule)

    override suspend fun deleteSchedule(id: Int) = scheduleDao.deleteSchedule(id)

    override fun SearchByCenterNameInSchedule(centerName: String): Flow<List<Schedule>> = scheduleDao.SearchByCenterNameInSchedule(centerName)

    override fun SearchByLocationInSchedule(location: String): Flow<List<Schedule>> =scheduleDao.SearchByLocationInSchedule(location)

    override fun SearchBySubjectInSchedule(subjectName: String): Flow<List<Schedule>> =scheduleDao.SearchBySubjectInSchedule(subjectName)

    override fun SearchByTeacherNameInSchedule(teacherName: String): Flow<List<Schedule>> =scheduleDao.SearchByTeacherNameInSchedule(teacherName)
}