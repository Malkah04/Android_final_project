package com.example.finalproject_tazkartm3aj.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finalproject_tazkartm3aj.allUI.login.LoginScreen
import com.example.finalproject_tazkartm3aj.allUI.login.LoginViewModel
import com.example.finalproject_tazkartm3aj.allUI.register.RegisterScreen
import com.example.finalproject_tazkartm3aj.allUI.register.RegisterViewModel
import com.example.finalproject_tazkartm3aj.allUI.scheduleList.ScheduleListVM
import com.example.finalproject_tazkartm3aj.allUI.scheduleList.ScheduleScreen
import com.example.finalproject_tazkartm3aj.allUI.screens.HomeScreen
import com.example.finalproject_tazkartm3aj.allUI.screens.NotificationScreen
import com.example.finalproject_tazkartm3aj.allUI.screens.StudentProfileScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "login",
        modifier = modifier
    ) {
        composable("login") {

            val loginViewModel: LoginViewModel = viewModel(factory = LoginViewModel.factory)
            LoginScreen(
                viewModel = loginViewModel,
                onRegisterClick = { navController.navigate("register") },
                onLoginSuccess = {
                    navController.navigate(Destination.HOME.route) {
                        popUpTo("login") { inclusive = true }
                    }
                },
            )
        }

        composable("register") {
            val registerViewModel: RegisterViewModel = viewModel(factory = RegisterViewModel.factory)

            RegisterScreen(
                viewModel = registerViewModel,
                onLoginClick = { navController.navigate("login") },
                onRegisterSuccess = {
                    navController.navigate(Destination.HOME.route) {
                        popUpTo("register") { inclusive = true }
                    }
                }
            )
        }

        Destination.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    Destination.HOME -> HomeScreen()
                    Destination.ScheduleScreen -> {
                        val scheduleVm: ScheduleListVM =viewModel(
                            factory = ScheduleListVM.factory
                        )
                        ScheduleScreen(scheduleVm)
                    }
                    Destination.NOTIFICATIONS -> NotificationScreen()
                    Destination.PROFILE -> StudentProfileScreen(1,Modifier)

                }
            }
        }
    }
}