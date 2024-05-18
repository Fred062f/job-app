package com.example.job_app.feature_feedback.api

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


object CustomOkHttpClient {
    fun create(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }
}