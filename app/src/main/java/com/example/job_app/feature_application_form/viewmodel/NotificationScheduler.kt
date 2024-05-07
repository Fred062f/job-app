package com.example.job_app.feature_application_form.viewmodel

import android.content.Context
import com.example.job_app.util.NotificationHelper

class NotificationScheduler(private val context: Context) {
    fun scheduleNotificationForApplication(dueDate: com.google.firebase.Timestamp) {
        val dueDateMillis = dueDate.seconds * 1000 + dueDate.nanoseconds / 1000000
        NotificationHelper.scheduleNotification(context, dueDateMillis, "Job Application Due", "Your application for the job is due today.")
    }
}
