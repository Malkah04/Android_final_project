package com.example.finalproject_tazkartm3aj.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalproject_tazkartm3aj.viewmodels.StudentProfileViewModel
import com.example.finalproject_tazkartm3aj.viewmodels.StudentVMFactory
import com.example.finalproject_tazkartm3aj.repository.studentRep.OfflineStudentRepository
import com.example.finalproject_tazkartm3aj.database.dDatabase
import androidx.compose.material3.TextField
import kotlinx.coroutines.launch

@Composable
fun StudentProfileScreen(studentId: Int, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val db = dDatabase.getDatabase(context)
    val repo = OfflineStudentRepository(db.studentDao())

    val vm: StudentProfileViewModel = viewModel(
        factory = StudentVMFactory(repo, studentId)
    )

    val student by vm.student.collectAsState()

    var isEditing by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    LaunchedEffect(student) {
        student?.let {
            name = it.name
            year = it.year
            phone = it.phone
        }
    }

    val scope = rememberCoroutineScope()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF0D47A1)
    ) {
        student?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = it.name.firstOrNull()?.toString() ?: "",
                        fontSize = 50.sp,
                        color = Color(0xFF0D47A1),
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {


                        Row(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "Name",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color(0xFF0D47A1)
                            )
                            if (isEditing) {
                                TextField(
                                    value = name,
                                    onValueChange = { name = it },
                                    singleLine = true,
                                    modifier = Modifier.width(150.dp)
                                )
                            } else {
                                Text(name, fontSize = 16.sp, color = Color(0xFF0D47A1))
                            }
                        }


                        Row(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "Year",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color(0xFF0D47A1)
                            )
                            if (isEditing) {
                                TextField(
                                    value = year,
                                    onValueChange = { year = it },
                                    singleLine = true,
                                    modifier = Modifier.width(150.dp)
                                )
                            } else {
                                Text(year, fontSize = 16.sp, color = Color(0xFF0D47A1))
                            }
                        }


                        Row(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "Phone",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color(0xFF0D47A1)
                            )
                            if (isEditing) {
                                TextField(
                                    value = phone,
                                    onValueChange = { phone = it },
                                    singleLine = true,
                                    modifier = Modifier.width(150.dp)
                                )
                            } else {
                                Text(phone, fontSize = 16.sp, color = Color(0xFF0D47A1))
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        if (isEditing) {
                            student?.let { s ->
                                val updatedStudent = s.copy(
                                    name = name,
                                    year = year,
                                    phone = phone
                                )

                                scope.launch {
                                    repo.updateInformationOfStudent(updatedStudent)
                                }
                            }
                        }
                        isEditing = !isEditing
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6F00)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (isEditing) "Save" else "Edit Profile", color = Color.White)
                }
            }
        }
    }
}
