package com.example.finalproject_Hagzakm3ak.allUI.addSchedule

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.finalproject_Hagzakm3ak.MyApp
import com.example.finalproject_Hagzakm3ak.database.ScheduleDatabaseDao
import com.example.finalproject_Hagzakm3ak.model.Schedule
import kotlinx.coroutines.launch

class AddScheduleVM(
    private val scheduleDao: ScheduleDatabaseDao
) : ViewModel() {

    val state = mutableStateOf("")
    val isProcessing = mutableStateOf(false)

    fun resetState() {
        state.value = ""
    }

    private fun addSchedule(schedule: Schedule) {
        viewModelScope.launch {
            try {
                scheduleDao.addSchedule(schedule)
                state.value = "success"
            } catch (e: Exception) {
                state.value = "Failed to add schedule"
            }
        }
    }

    fun addScheduleIfValid(
        subject: String,
        day: String,
        time: String,
        centerId: String,
        teacherId: String,
        imageUri: String?
    ) {
        viewModelScope.launch {
            isProcessing.value = true
            try {
                addSchedule(
                    Schedule(
                        subject = subject,
                        day = day,
                        time = time,
                        centerId = centerId.toInt(),
                        teacherId = teacherId.toInt(),
                        imageUri = imageUri
                    )
                )
            } catch (e: Exception) {
                state.value = "Invalid input data"
            }
            isProcessing.value = false
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY]
                val app = application as MyApp
                AddScheduleVM(app.database.scheduleDao())
            }
        }
    }
}
