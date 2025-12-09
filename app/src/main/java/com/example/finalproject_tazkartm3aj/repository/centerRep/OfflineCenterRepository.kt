package com.example.finalproject_tazkartm3aj.repository.centerRep

import com.example.finalproject_tazkartm3aj.database.CenterDatabaseDao
import com.example.finalproject_tazkartm3aj.model.Center
import kotlinx.coroutines.flow.Flow

class OfflineCenterRepository(private val centerDao : CenterDatabaseDao) : CenterRepository {
    override fun getAllCenters(): Flow<List<Center>> = centerDao.getAllCenters()

    override fun getCenterById(id: Int): Flow<Center?> = centerDao.getCenterById(id)

    override suspend fun addCenter(center: Center) = centerDao.addCenter(center)

    override suspend fun updateCenter(center: Center) =centerDao.updateCenter(center)

    override suspend fun deleteAll() = centerDao.deleteAll()

    override suspend fun deleteCenter(id: Int) =centerDao.deleteCenter(id)

    override fun searchByCenterName(name :String) =centerDao.searchByCenterName(name)

}