package com.example.job_app.feature_application_form.viewmodel

import android.content.Context
import com.example.job_app.util.NotificationHelper

// When "opret ansøgning" bliver triggered sammen med en dato
class NotificationScheduler(private val context: Context) {
    fun scheduleNotificationForApplication(dueDate: Long) {
        // Calculate the exact time to trigger the notification (e.g., 3 seconds from now)
        val triggerTime = System.currentTimeMillis() + 3000  // For immediate effect in testing

        // Prepare the title and content for the notification
        val title = "Application Reminder"
        val content = "Your application deadline is approaching!"

        // Schedule the notification
        NotificationHelper.scheduleNotification(context, triggerTime, title, content)
    }
}


