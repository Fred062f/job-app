package com.example.job_app.feature_feedback.api

import com.example.job_app.feature_feedback.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    @Volatile
    var INSTANCE: ApiInterface? = null
    fun getInstance(): ApiInterface {
        synchronized(this) {
            return INSTANCE ?: Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(CustomOkHttpClient.create())
                .build()
                .create(ApiInterface::class.java)
                .also {
                    INSTANCE = it
                }
        }
    }
}