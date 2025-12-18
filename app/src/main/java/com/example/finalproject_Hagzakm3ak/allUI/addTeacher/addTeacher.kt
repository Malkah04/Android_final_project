package com.example.finalproject_Hagzakm3ak.allUI.addTeacher

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import com.example.finalproject_Hagzakm3ak.allUI.addCenter.checkPhoneNumber


@Composable
fun AddTeacherPage(vm: AddTeacherVM , modifier: Modifier= Modifier){

    val state =vm.state.value

    var teacherName by remember { mutableStateOf("") }
    var teacherPhone  by remember { mutableStateOf("") }
    var teacherSubject  by remember { mutableStateOf("") }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color=Color(0xFF003366))
            .padding(16.dp)
    ) {
        Text(
            text = "Add Teacher",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }

    Column(modifier = modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            text = "Please add a new teacher",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        Spacer(modifier.height(50.dp))



        TextField(
            value = teacherName ,
            onValueChange = {teacherName =it},
            label = { Text("Name") },
            modifier= Modifier.fillMaxWidth()
        )
        Spacer(modifier.height(50.dp))


        TextField(
            value = teacherSubject ,
            onValueChange = {teacherSubject =it},
            label = { Text("subject") },
            modifier= Modifier.fillMaxWidth()
        )
        Spacer(modifier.height(50.dp))


        TextField(
            value = teacherPhone ,
            onValueChange = {teacherPhone =it},
            label = { Text("Phone") },
            modifier= Modifier.fillMaxWidth()
        )
        Spacer(modifier.height(50.dp))

        val phoneError =checkPhoneNumber(teacherPhone)
        if(teacherPhone.isNotEmpty() && phoneError!="ok"){
            Text(
                text = phoneError,
                color = MaterialTheme.colorScheme.error
            )
        }
        Button(
            onClick = {
                vm.addTeacherIfValid(teacherName, teacherSubject, teacherPhone)
            },
            colors = buttonColors(containerColor = Color(0xFFF1970E)),

            enabled = !vm.isProcessing.value && teacherName.isNotBlank() && teacherSubject.isNotBlank()
        ) {
            Text("Add Teacher")
        }




        if(state.isNotEmpty()){
            Text(
                text = if(state=="success") "Teacher added successfully" else "Failed to add teacher",
                color = if (state=="success") Color.Green else Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )

            LaunchedEffect(state) {
                kotlinx.coroutines.delay(2000)
                vm.resetState()
                teacherPhone =""
                teacherSubject =""
                teacherName =""
            }
        }
    }

}
