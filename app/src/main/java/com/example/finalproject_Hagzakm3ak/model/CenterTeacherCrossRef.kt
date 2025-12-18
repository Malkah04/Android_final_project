package com.example.finalproject_Hagzakm3ak.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.Junction
import androidx.room.Relation


@Entity(
    tableName = "centerTeacherCrossRef",
    primaryKeys = ["center_id","teacher_id"] ,
    indices = [Index(value = ["teacher_id"])] ,
    foreignKeys = [
        ForeignKey(
            entity = Center::class,
            parentColumns = ["_id"],
            childColumns = ["center_id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = Teacher::class,
            parentColumns = ["_id"],
            childColumns = ["teacher_id"],
            onDelete = CASCADE
        )
    ]
)
data class CenterTeacherCrossRef(
    @ColumnInfo(name ="center_id")
    val centerId: Int,
    @ColumnInfo(name ="teacher_id")
    val teacherId: Int
)

data class TeacherOfCenter(
    @Embedded
    val center: Center,
    @Relation(
        parentColumn = "_id",
        entityColumn = "_id",
        associateBy = Junction(
            value = CenterTeacherCrossRef::class,
            parentColumn = "center_id",
            entityColumn = "teacher_id"
        )
    )
    val teachers: List<Teacher>
)

data class CentersOfOneTeacher(
    @Embedded
    val teacher: Teacher,
    @Relation(
        parentColumn = "_id",
        entityColumn = "_id",
        associateBy = Junction(
            value = CenterTeacherCrossRef::class,
            parentColumn = "teacher_id",
            entityColumn = "center_id"
        )
    )
    val centers: List<Center>
)
