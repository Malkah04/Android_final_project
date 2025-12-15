package com.example.finalproject_tazkartm3aj

import android.app.Application
import com.example.finalproject_tazkartm3aj.database.dDatabase
import com.example.finalproject_tazkartm3aj.repository.scheduleRep.OfflineScheduleRepository
import com.example.finalproject_tazkartm3aj.repository.studentRep.OfflineStudentRepository

class MyApp : Application() {
    val database : dDatabase by lazy { dDatabase.getDatabase(this) }
    val scheduleRepository by lazy {
        OfflineScheduleRepository(database.scheduleDao())
    }
    val studentRepository by lazy {
        OfflineStudentRepository(database.studentDao())
    }
}