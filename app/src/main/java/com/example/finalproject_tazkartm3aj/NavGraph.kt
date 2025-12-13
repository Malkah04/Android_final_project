package com.example.finalproject_tazkartm3aj

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finalproject_tazkartm3aj.allUI.addCenter.AddCenterPage
import com.example.finalproject_tazkartm3aj.allUI.addCenter.AddCenterVM
import com.example.finalproject_tazkartm3aj.allUI.addTeacher.AddTeacherPage
import com.example.finalproject_tazkartm3aj.allUI.addTeacher.AddTeacherVM
import com.example.finalproject_tazkartm3aj.allUI.login.LoginScreen
import com.example.finalproject_tazkartm3aj.allUI.register.RegisterScreen

@Composable
fun AppNavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = "addCenter") {

        composable("login") {
            LoginScreen(
                onRegisterClick = { navController.navigate("register") },
                onLoginSuccess = { /* navigate to Home screen */ }
            )
        }

        composable("register") {
            RegisterScreen(
                onLoginClick = { navController.navigate("login") }
            )
        }

        composable("addCenter") {
            val vm: AddCenterVM = viewModel(factory = AddCenterVM.factory)
            AddCenterPage(
                vm = vm
            )
        }
        composable("addTeacher") {
            val vm : AddTeacherVM =viewModel(factory = AddTeacherVM.factory)
            AddTeacherPage(
                vm =vm
            )
        }
    }
}