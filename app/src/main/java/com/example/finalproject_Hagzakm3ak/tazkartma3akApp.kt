package com.example.finalproject_Hagzakm3ak

import android.app.Application
import com.example.finalproject_Hagzakm3ak.database.dDatabase
import com.example.finalproject_Hagzakm3ak.repository.scheduleRep.OfflineScheduleRepository
import com.example.finalproject_Hagzakm3ak.repository.studentRep.OfflineStudentRepository

class MyApp : Application() {
    val database : dDatabase by lazy { dDatabase.getDatabase(this) }
    val scheduleRepository by lazy {
        OfflineScheduleRepository(database.scheduleDao())
    }
    val studentRepository by lazy {
        OfflineStudentRepository(database.studentDao())
    }
}