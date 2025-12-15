package com.example.finalproject_tazkartm3aj.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.CoPresent
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlaylistAddCircle
import androidx.compose.ui.graphics.vector.ImageVector

enum class Destination(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    HOME("home", "Home", Icons.Default.Home, "Home"),

    ScheduleScreen("ScheduleScreen", "ScheduleScreen", Icons.Default.CoPresent, "Schedules"),
    NOTIFICATIONS("notifications", "Notifications", Icons.Default.Notifications, "Notifications"),
    PROFILE("profile", "Profile", Icons.Default.Person, "Profile")

}