package com.example.finalproject_Hagzakm3ak.allUI.addCenter

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
import androidx.navigation.NavHostController
import com.example.finalproject_Hagzakm3ak.allUI.login.LoginViewModel


@Composable
fun AddCenterPage(vm: AddCenterVM , modifier: Modifier= Modifier,
                  navController: NavHostController,
                  loginViewModel: LoginViewModel){

    val state =vm.state.value

    var centerName by remember { mutableStateOf("") }
    var centerAddress by remember { mutableStateOf("") }
    var centerPhone by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color=Color(0xFF003366))
            .padding(16.dp)
    ) {
        Text(
            text = "Add Center",
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
            text = "Please add a new center",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        Spacer(modifier.height(50.dp))



        TextField(
            value = centerName ,
            onValueChange = {centerName =it},
            label = { Text("Name") },
            modifier= Modifier.fillMaxWidth()
        )
        Spacer(modifier.height(50.dp))


        TextField(
            value = centerAddress ,
            onValueChange = {centerAddress =it},
            label = { Text("Address") },
            modifier= Modifier.fillMaxWidth()
        )
        Spacer(modifier.height(50.dp))


        TextField(
            value = centerPhone ,
            onValueChange = {centerPhone =it},
            label = { Text("Phone") },
            modifier= Modifier.fillMaxWidth()
        )
        Spacer(modifier.height(50.dp))

        val phoneError =checkPhoneNumber(centerPhone)
        if(centerPhone.isNotEmpty() && phoneError!="ok"){
            Text(
                text = phoneError,
                color = MaterialTheme.colorScheme.error
            )
        }
        Button(
            onClick = {
                vm.addCenterIfValid(centerName, centerAddress, centerPhone)
            },
            colors = buttonColors(containerColor = Color(0xFFF1970E)),

                    enabled = !vm.isProcessing.value && centerName.isNotBlank() && centerAddress.isNotBlank()
        ) {
            Text("Add Center")
        }
        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                loginViewModel.logout()
                navController.navigate("login") {
                    popUpTo(0)
                }
            },
            colors = buttonColors(
                contentColor = Color.White,
                containerColor = Color(0xFF003366)

            )
        ) {
            Text("Logout")
        }




        if(state.isNotEmpty()){
            Text(
                text = if(state=="success") "Center added successfully" else "Failed to add center",
                color = if (state=="success") Color.Green else Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )

            LaunchedEffect(state) {
                kotlinx.coroutines.delay(2000)
                vm.resetState()
                centerPhone =""
                centerAddress =""
                centerName =""
            }
        }
    }

}

fun checkPhoneNumber(phone:String) :String{
    if(phone.length<11){
        return  "Phone number must be 11 digits\""
    }
    val regex = Regex("^(010|011|012|015)[0-9]{8}$")
    if(!regex.matches(phone)){
        return "Invalid Phone Number"
    }
    return "ok"
}
