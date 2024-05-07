package com.example.job_app.feature_application_form.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.job_app.feature_home.repository.FirestoreRepository

class ApplicationFormViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApplicationFormViewModel::class.java)) {
            val firestoreRepository = FirestoreRepository()  // Assuming FirestoreRepository has a parameterless constructor
            val notificationScheduler = NotificationScheduler(context)  // Your NotificationScheduler must accept Context
            return ApplicationFormViewModel(firestoreRepository, notificationScheduler) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
