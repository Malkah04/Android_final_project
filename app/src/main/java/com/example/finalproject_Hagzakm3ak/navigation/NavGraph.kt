package com.example.finalproject_Hagzakm3ak.navigation

import HomeScreen
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finalproject_Hagzakm3ak.allUI.addCenter.AddCenterPage
import com.example.finalproject_Hagzakm3ak.allUI.addCenter.AddCenterVM
import com.example.finalproject_Hagzakm3ak.allUI.addSchedule.AddSchedulePage
import com.example.finalproject_Hagzakm3ak.allUI.addSchedule.AddScheduleVM
import com.example.finalproject_Hagzakm3ak.allUI.addTeacher.AddTeacherPage
import com.example.finalproject_Hagzakm3ak.allUI.addTeacher.AddTeacherVM
import com.example.finalproject_Hagzakm3ak.allUI.login.LoginScreen
import com.example.finalproject_Hagzakm3ak.allUI.login.LoginViewModel
import com.example.finalproject_Hagzakm3ak.allUI.register.RegisterScreen
import com.example.finalproject_Hagzakm3ak.allUI.register.RegisterViewModel
import com.example.finalproject_Hagzakm3ak.allUI.scheduleList.Edit
import com.example.finalproject_Hagzakm3ak.allUI.scheduleList.ScheduleListVM
import com.example.finalproject_Hagzakm3ak.allUI.scheduleList.ScheduleScreen
import com.example.finalproject_Hagzakm3ak.allUI.screens.StudentProfileScreen
import com.example.finalproject_Hagzakm3ak.allUI.singleSchedulePage.SinglePage
import com.example.finalproject_Hagzakm3ak.api.UserViewModel
import com.example.finalproject_Hagzakm3ak.api.UsersScreen

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
                    val dest = if (loginViewModel.Admin) adminLandingPage else userLandingPage
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

                    if (loginViewModel.loggedInEmail.equals("admin.gmail.com", ignoreCase = true)) {
                        loginViewModel.Admin = true
                    }

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
                    Destination.HOME -> if (!isAdmin) HomeScreen(
                        navController = navController
                    )
                    Destination.TeacherList -> if (!isAdmin) {
                        val userViewModel: UserViewModel =
                            viewModel(factory = UserViewModel.provideFactory())
                        UsersScreen(userViewModel)
                    }

                    Destination.PROFILE -> {
                        StudentProfileScreen(
                            email = loginViewModel.loggedInEmail ?: "",
                            navController = navController,
                            loginViewModel = loginViewModel
                        )
                    }

                    Destination.Schedule -> {
                        val scheduleVm: ScheduleListVM = viewModel(factory = ScheduleListVM.factory)
                        ScheduleScreen(
                            isAdmin = isAdmin,
                            ScheduleVM = scheduleVm,
                            onEditClick = { scheduleId ->
                                navController.navigate("edit_schedule/$scheduleId")
                            },
                            onClickDetails = { scheduleId ->
                                navController.navigate("details_screen/$scheduleId")
                            }
                        )
                    }
                    Destination.ADDCENTER -> if (isAdmin) {
                        val vm: AddCenterVM = viewModel(factory = AddCenterVM.factory)
                        AddCenterPage(
                            vm,
                            navController = navController,
                            loginViewModel = loginViewModel
                        )
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

        composable("schedule/{subjectName}") { backStackEntry ->
            val scheduleVm: ScheduleListVM = viewModel(factory = ScheduleListVM.factory)

            val subjectName = backStackEntry.arguments?.getString("subjectName")?.let {
                java.net.URLDecoder.decode(it, "UTF-8")
            } ?: ""

            ScheduleScreen(
                isAdmin = isAdmin,
                ScheduleVM = scheduleVm,
                onEditClick = { scheduleId ->
                    navController.navigate("edit_schedule/$scheduleId")
                },
                onClickDetails = { scheduleId ->
                    navController.navigate("details_screen/$scheduleId")
                },
                subject = subjectName
            )
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

        composable("details_screen/{scheduleId}") { backStackEntry ->
            val vm: ScheduleListVM =
                viewModel(factory = ScheduleListVM.factory)

            val scheduleId =
                backStackEntry.arguments?.getString("scheduleId")?.toIntOrNull() ?: -1

            val schedule = vm.getScheduleById(scheduleId ?: -1)

            if (schedule != null) {
                SinglePage(
                    id = scheduleId,
                    vm = vm
                )
            } else {
                Text("Schedule not found")
            }
        }
    }
}