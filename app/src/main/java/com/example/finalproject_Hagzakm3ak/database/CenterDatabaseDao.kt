package com.example.finalproject_Hagzakm3ak.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.finalproject_Hagzakm3ak.model.Center
import kotlinx.coroutines.flow.Flow

@Dao
interface CenterDatabaseDao {

    @Query("select * from Center")
    fun getAllCenters() : Flow<List<Center>>

    @Query("select * from center where _id =:id ")
    fun getCenterById(id :Int) : Flow<Center?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCenter(center:Center)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCenter(center: Center)

    @Query("delete from center")
    suspend fun deleteAll()

    @Query("delete from Center where _id =:id")
    suspend fun deleteCenter(id:Int)

    @Query("SELECT * FROM Center WHERE center_name LIKE '%' || :name || '%'")
    fun searchByCenterName(name:String): Flow<List<Center>>


    @Query("""
        SELECT EXISTS(
            SELECT 1 FROM Center
            WHERE center_name = :name
            AND center_address LIKE '%' || :address || '%'
        )
    """)
    suspend fun isCenterExist(name :String , address :String) : Boolean

}