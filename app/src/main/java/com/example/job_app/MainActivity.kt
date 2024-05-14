package com.example.job_app

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.example.job_app.feature_home.models.JobApplication
import com.example.job_app.navigation.Navigation
import com.example.job_app.ui.theme.JobappTheme
import com.example.job_app.util.NotificationHelper
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

class MainActivity : ComponentActivity() {
    // Launcher til at anmode om notifikationstilladelse
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    private var shouldScheduleNotification = false
    private var jobApplicationToSchedule: JobApplication? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Firebase.initialize(this)

        // Registrer resultat for notifikationstilladelse og informere via "Toast"
        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Notification permission granted", Toast.LENGTH_SHORT).show()
                jobApplicationToSchedule?.let { scheduleNotification(it) }
            } else {
                Toast.makeText(this, "Notification permission denied", Toast.LENGTH_SHORT).show()
            }
            jobApplicationToSchedule = null
        }


        setContent {
            JobappTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Navigation()
                }
            }
        }

        NotificationHelper.createNotificationChannel(this)
    }

    fun requestNotificationPermission(jobApplication: JobApplication) {
        shouldScheduleNotification = true
        jobApplicationToSchedule = jobApplication
        requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
    }
    private fun scheduleNotification(jobApplication: JobApplication) {
        NotificationHelper.scheduleNotification(
            this,
            jobApplication.timestamp?.seconds?.times(1000) ?: System.currentTimeMillis(),
            "Job Application Reminder",
            "Reminder for your job application: ${jobApplication.jobTitle}"
        )
    }
    fun scheduleNotificationWithPermissionCheck(jobApplication: JobApplication) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            requestNotificationPermission(jobApplication)
        } else {
            scheduleNotification(jobApplication)
        }
    }
}