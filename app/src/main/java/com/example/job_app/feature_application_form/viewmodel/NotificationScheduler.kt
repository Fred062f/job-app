package com.example.job_app.feature_application_form.viewmodel

import android.content.Context
import com.example.job_app.util.NotificationHelper

class NotificationScheduler(private val context: Context) {

    fun scheduleNotificationForApplication(title: String, content: String, timestampString: String) {
        // Attempt to parse the timestampString to Long
        val timestamp = timestampString.toLongOrNull()

        if (timestamp != null) {
            val currentTime = System.currentTimeMillis()
            val triggerTime24HoursBefore = timestamp - 24 * 60 * 60 * 1000

            // Log messages for debugging
            println("Current time: $currentTime, Deadline time: $timestamp, 24 hours before: $triggerTime24HoursBefore")

            // Schedule notification 24 hours before the deadline if it's in the future
            if (triggerTime24HoursBefore > currentTime) {
                NotificationHelper.scheduleNotification(
                    context,
                    triggerTime24HoursBefore,
                    "Job Application Reminder",
                    "Reminder for your job application: $title",
                    NotificationHelper.getUniqueNotificationId()
                )
            } else {
                println("24-hour reminder not scheduled because trigger time is less than current time")
            }

        } else {
            // Handle the case where the timestampString is not a valid number
            println("Invalid timestamp: $timestampString")
        }
    }
}
