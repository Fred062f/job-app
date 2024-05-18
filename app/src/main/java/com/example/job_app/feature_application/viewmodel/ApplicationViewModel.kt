package com.example.job_app.feature_application.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.job_app.feature_auth.repository.AuthRepository
import com.example.job_app.feature_home.models.JobApplication
import com.example.job_app.feature_home.repository.FirestoreRepository
import kotlinx.coroutines.launch

class ApplicationViewModel: ViewModel() {
    private val authRepository: AuthRepository = AuthRepository();
    private val firestoreRepository: FirestoreRepository = FirestoreRepository()

    var jobApplication = mutableStateOf<JobApplication?>(null)

    var id by mutableStateOf("")

    fun initialize(id: String) {
        this.id = id
        getJobApplication(id)
    }

    fun getJobApplication(applicationId: String) {
        viewModelScope.launch {
            val result = firestoreRepository.getDataByIdFromFirestore(authRepository.getCurrentUser()?.uid.toString(), applicationId)
            jobApplication.value = result
        }
    }
}