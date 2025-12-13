package com.example.finalproject_tazkartm3aj.allUI.addSchedule

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject_tazkartm3aj.database.ScheduleDatabaseDao
import com.example.finalproject_tazkartm3aj.model.Schedule
import kotlinx.coroutines.launch

// t_id =1 , subject = english
// t_id = 2  ,subject = physics

// c_id = 4
// c_id =5


class AddScheduleVM(private val scheduleDatabaseDao: ScheduleDatabaseDao) : ViewModel() {

    val state = mutableStateOf("")

    fun addSchedule(schedule: Schedule){
        viewModelScope.launch {
            try {
                scheduleDatabaseDao.addSchedule(schedule)
                state.value ="success"
            }catch (e: Exception){
                state.value ="fail"
            }
        }
    }


}