package com.example.finalproject_tazkartm3aj.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.finalproject_tazkartm3aj.model.Center
import com.example.finalproject_tazkartm3aj.model.CenterTeacherCrossRef
import com.example.finalproject_tazkartm3aj.model.Schedule
import com.example.finalproject_tazkartm3aj.model.Teacher
import com.example.finalproject_tazkartm3aj.model.Student
import com.example.finalproject_tazkartm3aj.model.StudentScheduleCrossRef

@Database(entities = [Center::class , Teacher::class , CenterTeacherCrossRef::class ,Student::class , Schedule::class , StudentScheduleCrossRef::class] , version = 2 , exportSchema = false)
abstract class dDatabase : RoomDatabase() {

    abstract fun centerDao() : CenterDatabaseDao
    abstract fun teacherDao() : TeacherDatabaseDao

    abstract fun studentDao(): StudentDatabaseDao
    abstract fun centerTeacher() : TeacherCenterDataBaseDao

    abstract fun scheduleDao() : ScheduleDatabaseDao

    abstract fun studentSchedule() : StudentScheduleDatabaseDao

    companion object{

        @Volatile
        private var Instance : dDatabase? =null

        fun getDatabase(context: Context) : dDatabase{
            return Instance ?:synchronized(this){
                Room.databaseBuilder(context , dDatabase::class.java,"app_database").fallbackToDestructiveMigration().build().also { Instance =it }
            }
        }
    }
}
