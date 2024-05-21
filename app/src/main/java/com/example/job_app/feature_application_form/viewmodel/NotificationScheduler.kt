package com.example.job_app.feature_application_form.viewmodel

import android.content.Context
import com.example.job_app.util.NotificationHelper

class NotificationScheduler(private val context: Context) {

    fun scheduleNotificationForApplication(title: String, content: String, timestampString: String) {
        val timestamp = timestampString.toLongOrNull()

        if (timestamp != null) {
            val currentTime = System.currentTimeMillis()
            val triggerTime24HoursBefore = timestamp - 24 * 60 * 60 * 1000

            // Debugging
            println("Current time: $currentTime, Deadline time: $timestamp, 24 hours before: $triggerTime24HoursBefore")

            // Schedule notification 24 hours before the deadline
            if (triggerTime24HoursBefore > currentTime) {
                NotificationHelper.scheduleNotification(
                    context,
                    triggerTime24HoursBefore,
                    "Der er 24 timer til deadline!",
                    "Husk at sende din ansøgning til: $title",
                    NotificationHelper.getUniqueNotificationId()
                )
            } else {
                println("24-hour reminder not scheduled because trigger time is less than current time")
            }

        } else {
            // Hvis timestampString ikke er et valid number
            println("Invalid timestamp: $timestampString")
        }
    }
}
