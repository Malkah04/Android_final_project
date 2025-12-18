package com.example.finalproject_Hagzakm3ak.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index


@Entity(
    tableName = "studentScheduleCrossRef",
    primaryKeys = ["student_id","schedule_id"],
    indices = [Index(value = ["student_id"])] ,
    foreignKeys = [
        ForeignKey(
            entity = Student::class,
            parentColumns = ["_id"],
            childColumns = ["student_id"],
            onDelete = CASCADE
        ), ForeignKey(
            entity = Schedule::class,
            parentColumns = ["_id"],
            childColumns = ["schedule_id"],
            onDelete = CASCADE
        )
    ]
)

data class StudentScheduleCrossRef (

    @ColumnInfo(name ="student_id")
    val StudentId :Int ,
    @ColumnInfo(name ="schedule_id")
    val ScheduleId :Int
)