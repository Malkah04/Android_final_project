package com.example.finalproject_Hagzakm3ak

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.finalproject_Hagzakm3ak.allUI.login.LoginViewModel
import com.example.finalproject_Hagzakm3ak.allUI.navBar.AppNavigationBar
import com.example.finalproject_Hagzakm3ak.ui.theme.FinalProjecttazkartM3ajTheme

class MainActivity : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinalProjecttazkartM3ajTheme {
                val navController = rememberNavController()
                val loginViewModel: LoginViewModel = viewModel(factory = LoginViewModel.factory)
                AppNavigationBar(isAdmin= loginViewModel.Admin ,Modifier)
                createNotificationChannel()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                        101
                    )
                }


            }
        }
   }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "notification_channel",
                "New Notifications",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Channel for schedule notifications"
            }
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

}

