package com.example.finalproject_Hagzakm3ak.model
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

   @ColumnInfo(name = "student_email")
   val email: String,

   @ColumnInfo(name = "student_password")
   val password: String,

    @ColumnInfo(name ="student_year")
    val year:String,

    @ColumnInfo(name = "student_image")
    val imagePath: String? = null

)
