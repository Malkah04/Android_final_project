package com.example.finalproject_tazkartm3aj

import android.app.Application
import com.example.finalproject_tazkartm3aj.database.dDatabase

class MyApp : Application() {
    val database : dDatabase by lazy { dDatabase.getDatabase(this) }
}