package com.example.finalproject_Hagzakm3ak.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Teacher")
data class Teacher(

    @PrimaryKey(autoGenerate = true)
    val _id :Int =0,

    @ColumnInfo(name = "teacher_name")
    val name :String,

    @ColumnInfo(name ="teacher_phone")
    val phone :String ,

    @ColumnInfo(name ="teacher_subject")
    val subject :String

)
