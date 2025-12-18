package com.example.finalproject_Hagzakm3ak.allUI.scheduleList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.finalproject_Hagzakm3ak.MyApp
import com.example.finalproject_Hagzakm3ak.model.Schedule
import com.example.finalproject_Hagzakm3ak.repository.centerRep.CenterRepository
import com.example.finalproject_Hagzakm3ak.repository.centerRep.OfflineCenterRepository
import com.example.finalproject_Hagzakm3ak.repository.scheduleRep.OfflineScheduleRepository
import com.example.finalproject_Hagzakm3ak.repository.scheduleRep.ScheduleRepository
import com.example.finalproject_Hagzakm3ak.repository.teacherRep.OfflineTeacherRepository
import com.example.finalproject_Hagzakm3ak.repository.teacherRep.TeacherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ScheduleListVM(
    private val scheduleRepository: ScheduleRepository,
    private val centerRepository: CenterRepository,
    private val teacherRepository: TeacherRepository
) : ViewModel() {

    val scheduleList = MutableStateFlow<List<Schedule>>(emptyList())
    init {
        getALlSchedule()
    }

    fun getALlSchedule() {
        viewModelScope.launch {
            try {
                scheduleRepository.getAllSchedules()
                    .collectLatest { schedules ->
                        scheduleList.value = schedules
                    }
            } catch (e: Exception) {
                Log.d("ScheduleListVM", "error $e")
            }
        }
    }

    suspend fun getCenterNameById(centerId: Int): String {
        return try {
            centerRepository.getCenterById(centerId).first()?.name ?: "unknown"
        } catch (e: Exception) {
            "unknown"
        }
    }

    suspend fun getCenterAddress(centerId: Int):String{
        return try {
            centerRepository.getCenterById(centerId).first()?.address ?:"no address"
        }catch (e: Exception){
            "no address"
        }
    }

    suspend fun getTeacherNameById(teacherId: Int): String {
        return try {
            teacherRepository.getTeacherById(teacherId).first()?.name ?: "unKnown"
        } catch (e: Exception) {
            "unKnown"
        }
    }

    fun Search(query: String): Flow<List<Schedule>> {
        val teacherSearchFlow = scheduleRepository.SearchByTeacherNameInSchedule(query)
        val centerSearchFlow = scheduleRepository.SearchByCenterNameInSchedule(query)
        val subjectSearchFlow = scheduleRepository.SearchBySubjectInSchedule(query)
        val locationSearchFlow = scheduleRepository.SearchByLocationInSchedule(query)
        return combine(
            teacherSearchFlow,
            centerSearchFlow,
            subjectSearchFlow,
            locationSearchFlow
        ) { t, c, s, l ->
            (t + c + s + l).distinctBy { it._id }
        }
    }

    fun editSchedule(updatedSchedule: Schedule) {
        viewModelScope.launch {
            scheduleRepository.updateSchedule(updatedSchedule)
            getALlSchedule()
        }
    }

    fun deleteSchedule(id: Int) {
        viewModelScope.launch {
            scheduleRepository.deleteSchedule(id)
            getALlSchedule()
        }
    }

    fun getScheduleById(id: Int): Schedule? {
        return scheduleList.value.firstOrNull { it._id == id }
    }
    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MyApp
                val scheduleRepo = OfflineScheduleRepository(application.database.scheduleDao())
                val teacherRepo = OfflineTeacherRepository(application.database.teacherDao())
                val centerRepo = OfflineCenterRepository(application.database.centerDao())

                ScheduleListVM(scheduleRepo, centerRepo, teacherRepo)
            }
        }
    }

}