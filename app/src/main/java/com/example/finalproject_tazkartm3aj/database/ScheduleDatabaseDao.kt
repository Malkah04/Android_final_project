package com.example.finalproject_tazkartm3aj.database
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.finalproject_tazkartm3aj.model.Schedule
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDatabaseDao {
    @Query("select * from schedule")
    fun getAllSchedules(): Flow<List<Schedule>>

    @Query("select * from schedule where _id = :id")
    fun getScheduleById(id: Int): Flow<Schedule>

    @Query("select * from schedule where student_id = :studentId")
    fun getSchedulesForStudent(studentId: Int): Flow<List<Schedule>>

    @Query("delete from schedule")
    suspend fun deleteAllSchedules()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSchedule(schedule: Schedule)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSchedule(schedule: Schedule)

    @Query("delete from schedule where _id = :id")
    suspend fun deleteSchedule(id: Int)


}