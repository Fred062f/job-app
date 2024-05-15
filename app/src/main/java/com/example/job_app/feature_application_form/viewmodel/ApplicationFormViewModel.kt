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
import java.text.ParseException

class ApplicationFormViewModel(
    private val firestoreRepository: FirestoreRepository,
    private val notificationScheduler: NotificationScheduler
) : ViewModel() {
    var jobTitle by mutableStateOf("")
    var date by mutableStateOf("Klik for at vælge dato")
    var description by mutableStateOf("")

    fun onJobTitleChange(newTitle: String) {
        jobTitle = newTitle
    }

    fun onDescriptionChange(newDescription: String) {
        description = newDescription
    }

    fun convertDateStringToTimestamp(): Timestamp? {
        if (date == "Klik for at vælge dato") {
            return null
        }
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return try {
            val parsedDate = format.parse(date)
            val calendar = Calendar.getInstance().apply {
                time = parsedDate
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }
            Timestamp(calendar.time)
        } catch (e: ParseException) {
            null
        }
    }

    fun addJobApplicationToList(jobApplication: JobApplication, userId: String, navigateBack: () -> Unit) {
        firestoreRepository.addJobApplicationToList(jobApplication, userId) {
            convertDateStringToTimestamp()?.let {
                notificationScheduler.scheduleNotificationForApplication(jobTitle,description,it.seconds.toString())
            }
            navigateBack()
        }
    }
}
