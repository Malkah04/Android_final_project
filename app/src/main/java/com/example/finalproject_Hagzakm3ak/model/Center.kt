package com.example.finalproject_Hagzakm3ak.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Center",
    indices = [androidx.room.Index(value = ["center_name"], unique = true)])
data class Center(

    @PrimaryKey(autoGenerate = true)
    val _id:Int =0,

    @ColumnInfo(name ="center_name")
    val name:String ,

    @ColumnInfo(name = "center_address")
    val address :String ,

    @ColumnInfo(name = "center_phone")
    val phone :String,

)
