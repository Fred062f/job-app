package com.example.job_app.feature_application_form.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.job_app.feature_home.repository.FirestoreRepository

class ApplicationFormViewModelFactory(
private val firestoreRepository: FirestoreRepository,
private val notificationScheduler: NotificationScheduler
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApplicationFormViewModel::class.java)) {
            return ApplicationFormViewModel(firestoreRepository, notificationScheduler) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

