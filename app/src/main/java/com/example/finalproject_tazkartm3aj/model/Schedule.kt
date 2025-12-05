package com.example.finalproject_tazkartm3aj.model
import androidx.compose.ui.text.font.Font
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity( tableName = "Schedule",
    foreignKeys = [
        ForeignKey(
            entity = Student::class,
            parentColumns = ["_id"],
            childColumns = ["student_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Schedule(

    @PrimaryKey(autoGenerate = true)
        val _id:Int=0,

    @ColumnInfo(name ="student_id")
        val studentId:Int,

    @ColumnInfo(name ="day")
        val day:String,

    @ColumnInfo(name ="time")
        val time:String,

    @ColumnInfo(name ="subject")
        val subject:String
)
