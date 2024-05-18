package com.example.job_app.feature_feedback.repository

import android.util.Log
import com.example.job_app.feature_feedback.api.ApiClient
import com.example.job_app.feature_feedback.model.ChatRequest
import com.example.job_app.feature_feedback.model.ChatResponse
import com.example.job_app.feature_feedback.model.Message
import com.example.job_app.feature_feedback.util.CHAT_GPT_MODEL
import retrofit2.Callback
import retrofit2.Response

class ChatRepository {
    private val apiClient = ApiClient.getInstance()

    interface ChatResponseListener {
        fun onSuccess(responseMessage: String)
        fun onError(errorMessage: String)
    }

    fun createChatCompletion(message: String, listener: ChatResponseListener) {
        try {
            val chatRequest = ChatRequest(
                messages = arrayListOf(
                    Message(
                        content = "Du har indsendt din jobansøgning til vurdering." +
                                "Jeg vil nu gennemgå din ansøgning og give dig ekspertvejledning og feedback. Fokus vil være på følgende områder:" +
                                "1. **Klarhed**: Er dine intentioner og budskaber tydelige?" +
                                "2. **Relevans**: Er dine kvalifikationer og erfaringer relevante for stillingen?" +
                                "3. **Sprogbrug**: Er sproget professionelt, korrekt og passende for stillingen?" +
                                "4. **Format**: Er ansøgningen velstruktureret og letlæselig?" +
                                "5. **Præsentation af kvalifikationer**: Hvordan præsenterer du dine kvalifikationer og erfaringer i forhold til den ønskede stilling?",
                        role = "system"
                    ),
                    Message(
                        content = message,
                        role = "user"
                    )
                ),
                model = CHAT_GPT_MODEL
            )
            apiClient.createChatCompletion(chatRequest).enqueue(object: Callback<ChatResponse> {
                override fun onResponse(
                    call: retrofit2.Call<ChatResponse>,
                    response: Response<ChatResponse>
                ) {
                    val code = response.code()
                    if (code == 200) {
                        response.body()?.choices?.get(0)?.message?.let {
                            Log.d("ChatRepository", "Response: $it")
                            listener.onSuccess(it.content)
                        }
                    } else {
                        Log.d("ChatRepository", "Error: $code")
                        listener.onError("Error: $code")
                    }
                }

                override fun onFailure(call: retrofit2.Call<ChatResponse>, t: Throwable) {
                    t.printStackTrace()
                    listener.onError("Error: ${t.message}")
                }

            })

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}