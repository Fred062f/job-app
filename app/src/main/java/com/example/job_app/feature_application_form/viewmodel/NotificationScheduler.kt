package com.example.job_app.feature_application_form.viewmodel

import android.content.Context
import com.example.job_app.util.NotificationHelper

// Når "opret ansøgning" bliver trigget sammen med en dato
class NotificationScheduler(private val context: Context) {

    fun scheduleNotificationForApplication(title: String, content: String) {
        // Tid er beregnet i millisekunder
        val triggerTime = System.currentTimeMillis() + 3000  // = 3 sek

        // Planlæg notifikationen
        NotificationHelper.scheduleNotification(context, triggerTime, title, content)
    }
}


