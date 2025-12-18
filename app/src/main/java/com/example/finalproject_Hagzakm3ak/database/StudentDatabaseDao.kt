package com.example.finalproject_Hagzakm3ak.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.finalproject_Hagzakm3ak.model.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDatabaseDao {
    @Query("select * from Student")
    fun getAllStudents(): Flow<List<Student>>

    @Query("select * from Student where _id = :id")
    fun getStudentById(id: Int): Flow<Student>

    @Query("delete from Student")
    suspend fun deleteAllStudents()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addStudent(student: Student)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateInformationOfStudent(student: Student)

    @Query("delete from Student where _id=:id")
    suspend fun deleteStudent(id:Int)

    @Query("SELECT * FROM Student WHERE student_email = :email LIMIT 1")
    suspend fun getStudentByEmail(email: String): Student?

    @Query("SELECT * FROM Student WHERE student_email = :email AND student_password = :password LIMIT 1")
    suspend fun login(email: String, password: String): Student?

}