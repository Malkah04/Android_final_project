package com.example.finalproject_Hagzakm3ak.allUI.addSchedule

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.finalproject_Hagzakm3ak.R

class AddScheduleWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {
        val result = inputData.getString("result") ?: "failed"

        if (result == "success") {
            showNotification("Booking App", "Schedule added successfully 🎉")
        } else {
            showNotification("Booking App", "Schedule add failed ❌")
        }

        return Result.success()
    }

    private fun showNotification(title: String, message: String) {
        val channelId = "notification_channel"

        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        with(NotificationManagerCompat.from(applicationContext)) {
            val manager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager
            manager.notify(1001, notification)        }
    }
}