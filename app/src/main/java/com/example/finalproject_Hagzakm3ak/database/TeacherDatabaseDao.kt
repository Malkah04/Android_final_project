package com.example.finalproject_Hagzakm3ak.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.finalproject_Hagzakm3ak.model.Teacher
import kotlinx.coroutines.flow.Flow

@Dao
interface TeacherDatabaseDao {

    @Query("select * from teacher")
    fun getAllTeacher() : Flow<List<Teacher>>

    @Query("select * from teacher where _id =:id")
    fun getTeacherById(id:Int) : Flow<Teacher>

    @Query("delete from teacher")
    fun deleteAllTeacher()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTeacher(teacher: Teacher)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateInformationOfTeacher(teacher: Teacher)

    @Query("delete from teacher where _id=:id")
    suspend fun deleteTeacher(id:Int)

    @Query("SELECT * FROM Teacher WHERE teacher_name LIKE '%' || :name || '%' ")
    fun searchByTeacherName(name:String) : Flow<List<Teacher>>

    @Query("""
        SELECT EXISTS(
            SELECT 1 FROM Teacher
            WHERE teacher_name = :name
            AND teacher_subject = :subject 
        )
    """)
    suspend fun isTeacherExist(name :String , subject :String) : Boolean
}