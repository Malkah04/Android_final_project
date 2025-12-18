package com.example.finalproject_Hagzakm3ak.allUI.scheduleList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.finalproject_Hagzakm3ak.model.Schedule


@Composable
fun Edit(
    schedule: Schedule,
    scheduleListVM: ScheduleListVM,
    navController: NavHostController
) {
    var subject by remember { mutableStateOf(schedule.subject) }
    var day by remember { mutableStateOf(schedule.day) }
    var time by remember { mutableStateOf(schedule.time) }
    var centerId by remember { mutableStateOf(schedule.centerId.toString()) }
    var teacherId by remember { mutableStateOf(schedule.teacherId.toString()) }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(subject, { subject = it }, label = { Text("Subject") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(16.dp))

        TextField(day, { day = it }, label = { Text("Day") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(16.dp))

        TextField(time, { time = it }, label = { Text("Time") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(16.dp))

        TextField(centerId, { centerId = it }, label = { Text("Center ID") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(16.dp))

        TextField(teacherId, { teacherId = it }, label = { Text("Teacher ID") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(20.dp))

        Button(onClick = {
            val updatedSchedule = schedule.copy(
                subject = subject,
                day = day,
                time = time,
                centerId = centerId.toIntOrNull() ?: schedule.centerId,
                teacherId = teacherId.toIntOrNull() ?: schedule.teacherId
            )
            scheduleListVM.editSchedule(updatedSchedule)
            navController.popBackStack()
        }) {
            Text("Save")
        }
    }
}