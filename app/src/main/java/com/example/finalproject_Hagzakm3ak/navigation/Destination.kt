package com.example.finalproject_Hagzakm3ak.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddHome
import androidx.compose.material.icons.filled.CoPresent
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.ui.graphics.vector.ImageVector

enum class Destination(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String,
    val admin : Boolean =false
) {
    HOME("home", "Home", Icons.Default.Home, "Home"),

    Schedule("Schedule", "Schedule", Icons.Default.CoPresent, "Schedules"),
//    BOOKING("booking", "Booking", Icons.Default.Schedule, "Booking"),

    TeacherList("taecherlist", "TeacherList", Icons.Default.AccountBox, "TeacherList"),
    PROFILE("profile", "Profile", Icons.Default.Person, "Profile"),

    ADDTEACHER("addTeacher" , "Add teacher" ,Icons.Default.CoPresent ,"Add teacher" , true),

    ADDCENTER("addCenter" ,"Add center" , Icons.Default.AddHome ,"Add center",true),

    ADDSCHEDULE("addSchedule" ,"Add schedule" , Icons.Default.Schedule ,"Add schedule" ,true)

}
