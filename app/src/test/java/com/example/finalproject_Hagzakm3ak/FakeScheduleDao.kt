package com.example.finalproject_Hagzakm3ak

import com.example.finalproject_Hagzakm3ak.database.ScheduleDatabaseDao
import com.example.finalproject_Hagzakm3ak.model.Schedule
import kotlinx.coroutines.flow.Flow

class FakeScheduleDao : ScheduleDatabaseDao {

    var isInserted = false
    override fun getAllSchedules(): Flow<List<Schedule>> {
        TODO("Not yet implemented")
    }

    override fun getScheduleById(id: Int): Flow<Schedule> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllSchedules() {
        TODO("Not yet implemented")
    }

    override suspend fun addSchedule(schedule: Schedule) {
        isInserted = true
    }

    override suspend fun updateSchedule(schedule: Schedule) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteSchedule(id: Int) {
        TODO("Not yet implemented")
    }

    override fun SearchByTeacherNameInSchedule(teacherName: String): Flow<List<Schedule>> {
        TODO("Not yet implemented")
    }

    override fun SearchBySubjectInSchedule(subjectName: String): Flow<List<Schedule>> {
        TODO("Not yet implemented")
    }

    override fun SearchByCenterNameInSchedule(centerName: String): Flow<List<Schedule>> {
        TODO("Not yet implemented")
    }

    override fun SearchByLocationInSchedule(location: String): Flow<List<Schedule>> {
        TODO("Not yet implemented")
    }
}
