package com.example.job_app.feature_home.models
import com.google.firebase.Timestamp

data class JobApplication(
    val description: String? = "Description",
    val jobTitle: String? = "Job title",
    val location: String? = "Location",
    val status: Boolean? = false,
    val timestamp: Timestamp? = null
)