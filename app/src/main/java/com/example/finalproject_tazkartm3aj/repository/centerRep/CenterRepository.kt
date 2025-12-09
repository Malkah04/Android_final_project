package com.example.finalproject_tazkartm3aj.repository.centerRep

import com.example.finalproject_tazkartm3aj.model.Center
import kotlinx.coroutines.flow.Flow

interface CenterRepository {

    fun getAllCenters() : Flow<List<Center>>

    fun getCenterById(id :Int) : Flow<Center?>

    suspend fun addCenter(center: Center)

    suspend fun updateCenter(center: Center)

    suspend fun deleteAll()

    suspend fun deleteCenter(id:Int)

    fun searchByCenterName(name:String): Flow<List<Center>>
}