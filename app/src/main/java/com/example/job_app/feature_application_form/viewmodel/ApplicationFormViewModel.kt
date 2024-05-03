package com.example.job_app.feature_application_form.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.job_app.feature_home.models.JobApplication
import com.example.job_app.feature_home.repository.FirestoreRepository
import kotlinx.coroutines.launch

class ApplicationFormViewModel: ViewModel() {
    private val firestoreRepository: FirestoreRepository = FirestoreRepository()

    var jobTitle by mutableStateOf("")
    var timestamp by mutableStateOf("")

    fun onJobTitleChange(jobTitle: String) {
        this.jobTitle = jobTitle;
    }
    fun addJobApplicationToList(jobApplication: JobApplication, userId: String, navigateBack: () -> Unit) {
        firestoreRepository.addJobApplicationToList(jobApplication, userId, navigateBack)
    }
}