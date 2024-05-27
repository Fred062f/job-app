package com.example.job_app.feature_feedback.model
// Frederik
data class ChatRequest(
    val messages: List<Message>,
    val model: String
)