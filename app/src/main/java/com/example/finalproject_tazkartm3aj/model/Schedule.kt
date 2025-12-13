package com.example.finalproject_tazkartm3aj.model
import androidx.compose.ui.text.font.Font
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity( tableName = "Schedule",
    foreignKeys = [
        ForeignKey(
            entity = Center::class,
            parentColumns = ["_id"],
            childColumns = ["center_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Teacher::class ,
            parentColumns = ["_id"],
            childColumns = ["teacher_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Schedule(

    @PrimaryKey(autoGenerate = true)
        val _id:Int=0,

    @ColumnInfo(name ="center_id")
        val centerId:Int,

    @ColumnInfo(name = "teacher_id")
        val teacherId :Int ,

    @ColumnInfo(name ="day")
        val day:String,

    @ColumnInfo(name ="lecture_time")
        val time:String,

    @ColumnInfo(name ="subject")
        val subject:String,

    @ColumnInfo(name = "image_uri")
        val imageUri: String? = null

)
