package com.example.finalproject_tazkartm3aj.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Student")
data class Student(

   @PrimaryKey(autoGenerate = true)
     val _id:Int=0,

    @ColumnInfo(name ="student_name")
    val name:String,

    @ColumnInfo(name ="student_phone")
    val phone:String,

    @ColumnInfo(name ="student_year")
    val year:String

)
