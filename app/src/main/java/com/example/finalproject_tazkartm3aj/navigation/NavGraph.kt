package com.example.finalproject_tazkartm3aj.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finalproject_tazkartm3aj.allUI.addCenter.AddCenterPage
import com.example.finalproject_tazkartm3aj.allUI.addCenter.AddCenterVM
import com.example.finalproject_tazkartm3aj.allUI.addSchedule.AddSchedulePage
import com.example.finalproject_tazkartm3aj.allUI.addSchedule.AddScheduleVM
import com.example.finalproject_tazkartm3aj.allUI.addTeacher.AddTeacherPage
import com.example.finalproject_tazkartm3aj.allUI.addTeacher.AddTeacherVM
import com.example.finalproject_tazkartm3aj.allUI.login.LoginScreen
import com.example.finalproject_tazkartm3aj.allUI.login.LoginViewModel
import com.example.finalproject_tazkartm3aj.allUI.register.RegisterScreen
import com.example.finalproject_tazkartm3aj.allUI.register.RegisterViewModel
import com.example.finalproject_tazkartm3aj.allUI.scheduleList.Edit
import com.example.finalproject_tazkartm3aj.allUI.scheduleList.ScheduleListVM
import com.example.finalproject_tazkartm3aj.allUI.scheduleList.ScheduleScreen
import com.example.finalproject_tazkartm3aj.allUI.screens.BookingScreen
import com.example.finalproject_tazkartm3aj.allUI.screens.HomeScreen
import com.example.finalproject_tazkartm3aj.allUI.screens.NotificationScreen
import com.example.finalproject_tazkartm3aj.allUI.screens.StudentProfileScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val loginViewModel: LoginViewModel = viewModel(factory = LoginViewModel.factory)
    val isAdmin: Boolean = loginViewModel.Admin

    val adminLandingPage = Destination.ADDCENTER.route
    val userLandingPage = Destination.HOME.route


    NavHost(
        navController = navController,
        startDestination = "login",
        modifier = modifier
    ) {
        composable("login") {

            LoginScreen(
                viewModel = loginViewModel,
                onRegisterClick = { navController.navigate("register") },
                onLoginSuccess = {
                    val dest =if(loginViewModel.Admin) adminLandingPage else userLandingPage
                    navController.navigate(dest) {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("register") {
            val registerViewModel: RegisterViewModel =
                viewModel(factory = RegisterViewModel.factory)

            RegisterScreen(
                viewModel = registerViewModel,
                onLoginClick = { navController.navigate("login") },
                onRegisterSuccess = {

                    loginViewModel.loggedInEmail = registerViewModel.email.trim()

                    val dest =
                        if (loginViewModel.Admin) adminLandingPage else userLandingPage

                    navController.navigate(dest) {
                        popUpTo("register") { inclusive = true }
                    }
                }
            )
        }


        Destination.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    Destination.HOME -> if (!isAdmin) HomeScreen()
                    Destination.NOTIFICATIONS -> if (!isAdmin) NotificationScreen()
                    Destination.PROFILE -> {
                        StudentProfileScreen(
                            email = loginViewModel.loggedInEmail ?: "",
                            navController = navController,
                            loginViewModel = loginViewModel
                        )
                    }


                    Destination.BOOKING -> if (!isAdmin) BookingScreen()
                    Destination.Schedule -> {
                        val scheduleVm: ScheduleListVM = viewModel(factory = ScheduleListVM.factory)
                        ScheduleScreen(
                            isAdmin = isAdmin,
                            ScheduleVM = scheduleVm,
                            onEditClick = { scheduleId ->
                                navController.navigate("edit_schedule/$scheduleId")
                            }
                        )
                    }
                    Destination.ADDCENTER -> if (isAdmin) {
                        val vm: AddCenterVM = viewModel(factory = AddCenterVM.factory)
                        AddCenterPage(vm)
                    }
                    Destination.ADDTEACHER -> if (isAdmin) {
                        val vm: AddTeacherVM = viewModel(factory = AddTeacherVM.factory)
                        AddTeacherPage(vm)
                    }
                    Destination.ADDSCHEDULE -> if (isAdmin) {
                        val vm: AddScheduleVM = viewModel(factory = AddScheduleVM.factory)
                        AddSchedulePage(vm)
                    }

                }
            }
        }
        composable("edit_schedule/{scheduleId}") { backStackEntry ->
            val scheduleVm: ScheduleListVM = viewModel(factory = ScheduleListVM.factory)
            val scheduleId = backStackEntry.arguments?.getString("scheduleId")?.toIntOrNull()
            val schedule = scheduleVm.getScheduleById(scheduleId ?: -1)

            if (schedule != null) {
                Edit(schedule = schedule, scheduleListVM = scheduleVm, navController = navController)
            } else {
                Text("Schedule not found")
            }
        }

    }
}