package com.example.finalproject_tazkartm3aj.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.finalproject_tazkartm3aj.model.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDatabaseDao {
    @Query("select * from student")
    fun getAllStudents(): Flow<List<Student>>

    @Query("select * from student where _id = :id")
    fun getStudentById(id: Int): Flow<Student>

    @Query("delete from student")
    suspend fun deleteAllStudents()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addStudent(student: Student)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateInformationOfStudent(student: Student)

    @Query("delete from student where _id=:id")
    suspend fun deleteStudent(id:Int)
}