package com.example.job_app.feature_application_form.viewmodel

import com.google.firebase.Timestamp

class applicationClass(
    val jobTitle: String = "Job title",
    val status: Boolean? = false,
    val timestamp: Timestamp? = null
)