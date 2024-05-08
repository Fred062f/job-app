package com.example.job_app.feature_home.models
import com.google.firebase.Timestamp

data class JobApplication(
    val id: String? = "Id",
    val jobTitle: String? = "Job title",
    val status: Boolean? = false,
    val timestamp: Timestamp? = null,
    val description: String? = "Ingen beskrivelse",
)