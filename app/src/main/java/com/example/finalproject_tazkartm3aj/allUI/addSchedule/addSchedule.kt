package com.example.finalproject_tazkartm3aj.allUI.addSchedule

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun AddSchedulePage(
    vm: AddScheduleVM,
    modifier: Modifier = Modifier
) {

    val state = vm.state.value

    var subject by remember { mutableStateOf("") }
    var day by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var centerId by remember { mutableStateOf("") }
    var teacherId by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<String?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        imageUri = uri?.toString()
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp)
    ) {
        Text(
            text = "Add Schedule",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Please add a new schedule",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(bottom = 24.dp)
        )

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

        Button(onClick = { imagePicker.launch("image/*") }) {
            Text("Choose Image")
        }

        imageUri?.let {
            Spacer(Modifier.height(16.dp))
            AsyncImage(
                model = it,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                vm.addScheduleIfValid(
                    subject,
                    day,
                    time,
                    centerId,
                    teacherId,
                    imageUri
                )
            },
            enabled = !vm.isProcessing.value &&
                    subject.isNotBlank() &&
                    day.isNotBlank() &&
                    time.isNotBlank() &&
                    centerId.isNotBlank() &&
                    teacherId.isNotBlank()
        ) {
            Text("Add Schedule")
        }

        if (state.isNotEmpty()) {
            Text(
                text = if (state == "success") "Schedule added successfully" else state,
                color = if (state == "success") Color.Green else Color.Red,
                modifier = Modifier.padding(top = 12.dp)
            )

            LaunchedEffect(state) {
                kotlinx.coroutines.delay(2000)
                vm.resetState()
                subject = ""
                day = ""
                time = ""
                centerId = ""
                teacherId = ""
                imageUri = null
            }
        }
    }
}
