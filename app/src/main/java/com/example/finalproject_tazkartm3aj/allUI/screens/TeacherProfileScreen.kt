package com.example.finalproject_tazkartm3aj.allUI.screens

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
import com.example.finalproject_tazkartm3aj.allUI.screens.viewmodels.TeacherProfileViewModel
import com.example.finalproject_tazkartm3aj.repository.teacherRep.OfflineTeacherRepository
import com.example.finalproject_tazkartm3aj.database.dDatabase
import androidx.compose.material3.TextField
import kotlinx.coroutines.launch
@Composable
fun TeacherProfileScreen(
    teacherId: Int,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val db = dDatabase.getDatabase(context)
    val repo = OfflineTeacherRepository(db.teacherDao())

    val vm: TeacherProfileViewModel =
        viewModel(factory = TeacherProfileViewModel.factory(repo))

    val teacher by vm.teacher.collectAsState()

    var isEditing by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }

    LaunchedEffect(teacherId) {
        vm.loadTeacherById(teacherId)
    }

    LaunchedEffect(teacher) {
        teacher?.let {
            name = it.name
            phone = it.phone
            subject = it.subject
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        when (val t = teacher) {
            null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(modifier = Modifier.height(24.dp))

                    Card(
                        shape = CircleShape,
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(120.dp)
                                .background(Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = t.name.firstOrNull()?.uppercase() ?: "",
                                fontSize = 48.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF0D47A1)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Card(
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Name",
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF0D47A1)
                                )
                                if (isEditing) {
                                    TextField(
                                        value = name,
                                        onValueChange = { name = it },
                                        singleLine = true,
                                        modifier = Modifier.width(160.dp)
                                    )
                                } else {
                                    Text(name, color = Color(0xFF0D47A1))
                                }
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Phone",
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF0D47A1)
                                )
                                if (isEditing) {
                                    TextField(
                                        value = phone,
                                        onValueChange = { phone = it },
                                        singleLine = true,
                                        modifier = Modifier.width(160.dp)
                                    )
                                } else {
                                    Text(phone, color = Color(0xFF0D47A1))
                                }
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Subject",
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF0D47A1)
                                )
                                if (isEditing) {
                                    TextField(
                                        value = subject,
                                        onValueChange = { subject = it },
                                        singleLine = true,
                                        modifier = Modifier.width(160.dp)
                                    )
                                } else {
                                    Text(subject, color = Color(0xFF0D47A1))
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            if (isEditing) {
                                vm.updateTeacher(
                                    t.copy(
                                        name = name,
                                        phone = phone,
                                        subject = subject
                                    )
                                )
                            }
                            isEditing = !isEditing
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF6F00)
                        )
                    ) {
                        Text(
                            text = if (isEditing) "Save" else "Edit Profile",
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
