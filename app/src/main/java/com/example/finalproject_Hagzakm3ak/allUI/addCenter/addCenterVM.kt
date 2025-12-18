package com.example.finalproject_Hagzakm3ak.allUI.addCenter

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.example.finalproject_Hagzakm3ak.database.CenterDatabaseDao
import com.example.finalproject_Hagzakm3ak.model.Center
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.finalproject_Hagzakm3ak.MyApp
import kotlinx.coroutines.flow.first

import kotlinx.coroutines.launch

class AddCenterVM(private val centerDatabaseDao: CenterDatabaseDao) : ViewModel() {

    init {
        viewModelScope.launch {
            try {
                val centersList = centerDatabaseDao.getAllCenters().first()
                if (centersList.isEmpty()) {
                    Log.d("DB_DEBUG", "No Centers found in the database.")
                } else {
                    centersList.forEach {
                        Log.d("DB_DEBUG", "Center:${it._id}, ${it.name}, ${it.address}, ${it.phone}")
                    }
                }
            } catch (e: Exception) {
                Log.e("DB_DEBUG", "Error reading centers from Flow: ${e.message}")
            }
        }
    }
    val state = mutableStateOf("")
    val isProcessing =mutableStateOf(false)

    fun addCenter(center: Center){
        viewModelScope.launch{
            try {
                centerDatabaseDao.addCenter(center)
                state.value ="success"

            }catch (e: Exception){
                state.value ="fail"
            }

        }
    }
    fun resetState(){
        state.value=""
    }
    suspend fun isCenterExist(name :String ,address :String) : Boolean{
            return centerDatabaseDao.isCenterExist(name ,address)
    }


    fun addCenterIfValid(name: String, address: String, phone: String) {
        viewModelScope.launch {
            isProcessing.value = true

            val phoneValid = checkPhoneNumber(phone) == "ok"
            val centerExists = isCenterExist(name, address)

            if (!phoneValid) {
                state.value = "Invalid phone number"
            } else if (centerExists) {
                state.value = "Center already exists!"
            } else {
                addCenter(Center(name = name, address = address, phone = phone))
            }

            isProcessing.value = false
        }
    }
    companion object{
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =this[APPLICATION_KEY]
                val app = application as MyApp
                AddCenterVM(app.database.centerDao())
            }
        }
    }
}