package com.example.finalproject_Hagzakm3ak.allUI.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.finalproject_Hagzakm3ak.allUI.login.LoginViewModel
import com.example.finalproject_Hagzakm3ak.allUI.screens.viewmodels.StudentProfileViewModel

@Composable
fun StudentProfileScreen(
    email: String,
    navController: NavHostController,
    loginViewModel: LoginViewModel,
) {
    val vm: StudentProfileViewModel =
        viewModel(factory = StudentProfileViewModel.factory())

    val student by vm.student.collectAsState()

    var isEditing by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    LaunchedEffect(email) {
        if (email.isNotBlank()) {
            vm.loadStudentByEmail(email)
        }
    }

    LaunchedEffect(student) {
        student?.let {
            name = it.name
            year = it.year
            phone = it.phone
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        when (val s = student) {
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
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(120.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = s.name.firstOrNull()?.uppercase() ?: "",
                                fontSize = 48.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF003366)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Card(
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
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
                                    color = Color(0xFF003366)
                                )

                                if (isEditing) {
                                    TextField(
                                        value = name,
                                        onValueChange = { name = it },
                                        singleLine = true,
                                        modifier = Modifier.width(160.dp)
                                    )
                                } else {
                                    Text(name, color = Color(0xFF003366))
                                }
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Year",
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF003366)
                                )

                                if (isEditing) {
                                    TextField(
                                        value = year,
                                        onValueChange = { year = it },
                                        singleLine = true,
                                        modifier = Modifier.width(160.dp)
                                    )
                                } else {
                                    Text(year, color = Color(0xFF003366))
                                }
                            }

                            // PHONE
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Phone",
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF003366)
                                )

                                if (isEditing) {
                                    TextField(
                                        value = phone,
                                        onValueChange = { phone = it },
                                        singleLine = true,
                                        modifier = Modifier.width(160.dp)
                                    )
                                } else {
                                    Text(phone, color = Color(0xFF003366))
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            if (isEditing) {
                                vm.updateStudent(
                                    s.copy(
                                        name = name,
                                        year = year,
                                        phone = phone
                                    )
                                )
                            }
                            isEditing = !isEditing
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF003366)
                        )
                    ) {
                        Text(
                            text = if (isEditing) "Save" else "Edit Profile",
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedButton(
                        onClick = {
                            loginViewModel.logout()
                            navController.navigate("login") {
                                popUpTo(0)
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.White,
                            containerColor = Color(0xFFF1970E)

                        )
                    ) {
                        Text("Logout")
                    }
                }
            }
        }
    }
}
