package com.example.job_app.feature_feedback.api

import com.example.job_app.BuildConfig
import com.example.job_app.feature_feedback.model.ChatRequest
import com.example.job_app.feature_feedback.model.ChatResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
// Frederik
interface ApiInterface {
    @POST("chat/completions")
    fun createChatCompletion(
        @Body chatRequest: ChatRequest,
        @Header("Content_Type") contentType: String = "application/json",
        @Header("Authorization") authorization: String = "Bearer ${BuildConfig.API_KEY}"
    ): Call<ChatResponse>
}