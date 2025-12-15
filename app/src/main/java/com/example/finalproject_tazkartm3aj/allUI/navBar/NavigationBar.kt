package com.example.finalproject_tazkartm3aj.allUI.navBar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.finalproject_tazkartm3aj.navigation.AppNavGraph
import com.example.finalproject_tazkartm3aj.navigation.Destination
import com.example.finalproject_tazkartm3aj.ui.theme.FinalProjecttazkartM3ajTheme

@Composable
fun AppNavigationBar(isAdmin : Boolean =false, modifier: Modifier = Modifier ) {
    val navController = rememberNavController()
    var selectedRoute by rememberSaveable { mutableStateOf(Destination.HOME.route) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val userList = listOf(
        Destination.HOME,
        Destination.ScheduleScreen,
        Destination.NOTIFICATIONS,
        Destination.PROFILE,
        Destination.BOOKING,
    )

    val adminList =listOf(
        Destination.ADDCENTER,
        Destination.ADDTEACHER,
        Destination.ADDSCHEDULE,
        Destination.ScheduleScreen
    )

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (currentRoute != "login" && currentRoute != "register") {
                NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {

                    val list = if(isAdmin) adminList else userList
                    list.forEach { destination ->
                        NavigationBarItem(
                            selected = selectedRoute == destination.route,
                            onClick = {
                                navController.navigate(destination.route) {
                                    launchSingleTop = true
                                }
                                selectedRoute = destination.route
                            },
                            icon = {
                                Icon(
                                    destination.icon,
                                    contentDescription = destination.contentDescription,
                                    tint = if (selectedRoute == destination.route)
                                        Color(0xFFF1970E)
                                    else
                                        Color(0xFF003366)
                                )

                            },
                            label = { Text(destination.label,color= Color(0xFF003366)) }
                        )
                    }
                }
            }
        }
    ) { contentPadding ->
        AppNavGraph(
            navController = navController,
            modifier = Modifier.padding(contentPadding)
        )
    }
}
@Preview
@Composable
fun TestNavBar(){
    FinalProjecttazkartM3ajTheme {
        AppNavigationBar()
    }

}