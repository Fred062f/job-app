package com.example.job_app.feature_feedback.model

data class ChatRequest(
    val messages: List<Message>,
    val model: String
)