package com.example.job_app.util

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

object NotificationHelper {
    private const val CHANNEL_ID = "job_application_channel"
    private const val PERMISSION_REQUEST_CODE = 101

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Job Application Notifications"
            val descriptionText = "Notifications for job application deadlines"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun requestNotificationPermission(activity: ComponentActivity, onPermissionResult: (Boolean) -> Unit) {
        val requestPermissionLauncher = activity.registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            onPermissionResult(isGranted)
        }

        requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
    }

    fun scheduleNotification(context: Context, triggerTime: Long, title: String, content: String) {
        val notificationManager: NotificationManagerCompat = NotificationManagerCompat.from(context)
        if (!notificationManager.areNotificationsEnabled()) {
            // Notifications are disabled for the app
            return
        }

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setWhen(triggerTime)
            .setAutoCancel(true)

        notificationManager.notify(1, builder.build())
    }
}
