package com.example.job_app.feature_application_form.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.net.ParseException
import androidx.lifecycle.ViewModel
import com.example.job_app.feature_home.models.JobApplication
import com.example.job_app.feature_home.repository.FirestoreRepository
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ApplicationFormViewModel: ViewModel() {
    private val firestoreRepository: FirestoreRepository = FirestoreRepository()

    var jobTitle by mutableStateOf("")
    var description by mutableStateOf("")
    var date by mutableStateOf("Klik for at vælge dato")

    fun convertDateStringToTimestamp(): Timestamp {
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = format.parse(date)

        val calendar = Calendar.getInstance()
        calendar.time = date!!
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        // Convert the date to a Firebase Timestamp
        val timestamp = Timestamp(calendar.time)
        return timestamp
    }

    fun onJobTitleChange(jobTitle: String) {
        this.jobTitle = jobTitle;
    }

    fun onDescriptionChange(description: String) {
        this.description = description;
    }

    var shouldShowDialogOnJobTitleError by mutableStateOf(false)

    var shouldShowDialogOnDateError by mutableStateOf(false)

    fun addJobApplicationToList(jobApplication: JobApplication, userId: String, navigateBack: () -> Unit) {
        firestoreRepository.addJobApplicationToList(jobApplication, userId, navigateBack)
    }
}