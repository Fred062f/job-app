package com.example.job_app.feature_application_form.viewmodel
// Victor
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.job_app.feature_home.repository.FirestoreRepository
// Factory bruges da vi har flere parametre til de ting vi vil have, at ViewModel skal indeholde
// Her sammensættes både FirestoreRepository og NotificationScheduler til en enkelt ViewModel
class ApplicationFormViewModelFactory(
    private val notificationScheduler: NotificationScheduler
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApplicationFormViewModel::class.java)) {
            return ApplicationFormViewModel(notificationScheduler) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


