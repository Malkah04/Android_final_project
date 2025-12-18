package com.example.finalproject_Hagzakm3ak.database
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.finalproject_Hagzakm3ak.model.Schedule
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDatabaseDao {
    @Query("select * from schedule")
    fun getAllSchedules(): Flow<List<Schedule>>

    @Query("select * from schedule where _id = :id")
    fun getScheduleById(id: Int): Flow<Schedule>

    @Query("delete from schedule")
    suspend fun deleteAllSchedules()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSchedule(schedule: Schedule)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSchedule(schedule: Schedule)

    @Query("delete from schedule where _id = :id")
    suspend fun deleteSchedule(id: Int)

    @Query("""
        select
            S.*
        from
            Schedule S
        inner join
            Teacher T
        on
            S.teacher_id = T._id
        where
            T.teacher_name like '%' || :teacherName || '%'
    """)
    fun SearchByTeacherNameInSchedule(teacherName:String): Flow<List<Schedule>>

    @Query("select * from schedule where subject like '%' ||:subjectName || '%' ")
    fun SearchBySubjectInSchedule(subjectName:String) : Flow<List<Schedule>>

    @Query("""
        select 
        S.*
        from 
            Schedule S
        inner join
            Center C
        on 
            S.center_id =C._id
        where
            C.center_name like '%' || :centerName || '%'
    """)
    fun SearchByCenterNameInSchedule(centerName:String) : Flow<List<Schedule>>

    @Query("""
        select
            S.*
        from
            Schedule S
        inner join
            Center C
        on
            S.center_id =C._id
        where 
            C.center_address like '%' || :location || '%'
    """)
    fun SearchByLocationInSchedule(location :String) :Flow<List<Schedule>>




}