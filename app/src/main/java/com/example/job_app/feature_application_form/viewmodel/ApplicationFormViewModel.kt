package com.example.job_app.feature_application_form.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.job_app.feature_home.models.JobApplication
import com.example.job_app.feature_home.repository.FirestoreRepository
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ApplicationFormViewModel(
    private val firestoreRepository: FirestoreRepository,
    private val notificationScheduler: NotificationScheduler
) : ViewModel() {
    var jobTitle by mutableStateOf("")
    var date by mutableStateOf("Klik for at vælge dato")

    fun convertDateStringToTimestamp(): Timestamp {
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val parsedDate = format.parse(date) ?: return Timestamp.now()

        val calendar = Calendar.getInstance().apply {
            time = parsedDate
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        return Timestamp(calendar.time)
    }

    fun onJobTitleChange(jobTitle: String) {
        this.jobTitle = jobTitle
    }

    fun addJobApplicationToList(jobApplication: JobApplication, userId: String, navigateBack: () -> Unit) {
        firestoreRepository.addJobApplicationToList(jobApplication, userId) {
            notificationScheduler.scheduleNotificationForApplication(convertDateStringToTimestamp())
            navigateBack()
        }
    }
}
