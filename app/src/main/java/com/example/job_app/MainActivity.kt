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

    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    private var shouldScheduleNotification = false
    private var jobApplicationToSchedule: JobApplication? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Firebase.initialize(this)

        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Notifikation adgang givet", Toast.LENGTH_SHORT).show()
                jobApplicationToSchedule?.let {
                    scheduleNotifications(it)
                }
            } else {
                Toast.makeText(this, "Notifikation adgang nægtet", Toast.LENGTH_SHORT).show()
            }
            shouldScheduleNotification = false
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

    private fun scheduleNotifications(jobApplication: JobApplication) {
        val triggerTime = jobApplication.timestamp?.seconds?.times(1000) ?: System.currentTimeMillis()
        val currentTime = System.currentTimeMillis()
        val triggerTime24HoursBefore = triggerTime - 24 * 60 * 60 * 1000

        // Notifikation for 24 timer før deadline
        if (triggerTime24HoursBefore > currentTime) {
            NotificationHelper.scheduleNotification(
                this,
                triggerTime24HoursBefore,
                "Husk din ansøgning!",
                "Du har mindre end 24 timer til at aflevere din ansøgning til: ${jobApplication.jobTitle}",
                NotificationHelper.getUniqueNotificationId()
            )
        } else {
            NotificationHelper.scheduleNotification(
                this,
                currentTime + 5000,  // Planlæg en notifikation om 5 sekunder, hvis deadline er mindre end 24 timer væk
                "Din ansøgning skal afleveres i dag!",
                "Du har mindre end 24 timer til at aflevere din ansøgning til: ${jobApplication.jobTitle}",
                NotificationHelper.getUniqueNotificationId()
            )
        }
    }

    fun scheduleNotificationWithPermissionCheck(jobApplication: JobApplication) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            requestNotificationPermission(jobApplication)
        } else {
            scheduleNotifications(jobApplication)
        }
    }
}
