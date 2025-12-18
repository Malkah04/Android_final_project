package com.example.finalproject_Hagzakm3ak.allUI.singleSchedulePage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.finalproject_Hagzakm3ak.allUI.scheduleList.ScheduleListVM


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SinglePage(id:Int,
               vm : ScheduleListVM,
               onBookClick: (() -> Unit)? = null,
               modifier: Modifier = Modifier ) {

    var centerName by remember { mutableStateOf("") }
    var centerAddress by remember { mutableStateOf("") }

    LaunchedEffect(id) {
        val schedule = vm.getScheduleById(id)
        if (schedule != null) {
            centerName = vm.getCenterNameById(schedule.centerId)
            centerAddress = vm.getCenterAddress(schedule.centerId)
        }
    }
    val schedule = vm.getScheduleById(id)
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Schedule Details",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            )
        }
    ) { paddingValues ->

        if (schedule == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    text = schedule.subject,
                    style = MaterialTheme.typography.headlineSmall
                )

                Text(
                    text = "Center Name: ${centerName}",
                    style = MaterialTheme.typography.bodyLarge
                )

                Divider()

                Text(
                    text = "Date: ${schedule.day}",
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = "Time: ${schedule.time}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(text = "Address: $centerAddress",
                    style = MaterialTheme.typography.bodyLarge
                )

                Button(
                    onClick = { onBookClick?.invoke() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF1970E)),
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text(text = "Book", color = Color.White)
                }

            }
        }
    }
}
