package com.example.finalproject_Hagzakm3ak.database


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import com.example.finalproject_Hagzakm3ak.model.CenterTeacherCrossRef
import com.example.finalproject_Hagzakm3ak.model.TeacherOfCenter
import com.example.finalproject_Hagzakm3ak.model.CentersOfOneTeacher

@Dao
interface TeacherCenterDataBaseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTeacherCenter(crossRef:CenterTeacherCrossRef )

    @Delete
    suspend fun deleteTeacherCenter(crossRef:CenterTeacherCrossRef )

    @Transaction
    @Query("select * from Center where _id =:centerId")
    fun getAllTeacherOfCenter(centerId :Int) : Flow<TeacherOfCenter>

    @Transaction
    @Query("select * from Teacher where _id =:teacherId")
    fun getCenterOfTeacher(teacherId :Int) :Flow<CentersOfOneTeacher>

}