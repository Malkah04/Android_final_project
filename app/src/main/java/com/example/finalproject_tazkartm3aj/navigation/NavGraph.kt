package com.example.finalproject_tazkartm3aj.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finalproject_tazkartm3aj.allUI.login.LoginScreen
import com.example.finalproject_tazkartm3aj.allUI.register.RegisterScreen
import com.example.finalproject_tazkartm3aj.allUI.screens.HomeScreen
import com.example.finalproject_tazkartm3aj.allUI.screens.NotificationScreen
import com.example.finalproject_tazkartm3aj.allUI.screens.StudentProfileScreen
import com.example.finalproject_tazkartm3aj.allUI.screens.TeachersList
import com.example.finalproject_tazkartm3aj.allUI.screens.TeachersScreen
import com.example.finalproject_tazkartm3aj.navigation.Destination

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
            LoginScreen(
                onRegisterClick = { navController.navigate("register") },
                onLoginSuccess = {
                    navController.navigate(Destination.HOME.route) {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("register") {
            RegisterScreen(
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
                    Destination.TEACHERSLIST -> TeachersScreen()
                    Destination.NOTIFICATIONS -> NotificationScreen()
                    Destination.PROFILE -> StudentProfileScreen(1,Modifier)

                }
            }
        }
    }
}