package com.example.job_app.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.util.concurrent.atomic.AtomicInteger

object NotificationHelper {
    private const val CHANNEL_ID = "job_application_channel"
    private val notificationId = AtomicInteger(0)

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

    fun scheduleNotification(context: Context, triggerTime: Long, title: String, content: String, notificationId: Int) {
        val notificationManager: NotificationManagerCompat = NotificationManagerCompat.from(context)
        if (!notificationManager.areNotificationsEnabled()) {
            // Tilladelse er ikke givet
            return
        }

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setWhen(triggerTime)
            .setAutoCancel(true)

        // Planlægger notifikationen
        notificationManager.notify(notificationId, builder.build())
    }

    fun getUniqueNotificationId(): Int {
        return notificationId.incrementAndGet()
    }
}
