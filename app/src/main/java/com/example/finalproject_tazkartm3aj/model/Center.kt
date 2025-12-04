package com.example.finalproject_tazkartm3aj.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Center")
data class Center(

    @PrimaryKey
    val _id:Int =0,

    @ColumnInfo(name ="center_name")
    val name:String ,

    @ColumnInfo(name = "center_address")
    val address :String ,

    @ColumnInfo(name = "center_phone")
    val phone :String,

)
