package com.example.job_app.feature_auth.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.job_app.feature_auth.repository.AuthRepository

class LoginViewModel : ViewModel() {
    private val authRepository: AuthRepository = AuthRepository();

    var username by mutableStateOf("")

    var password by mutableStateOf("")

    fun onUsernameChange(username: String) {
        this.username = username;
    }

    fun onPasswordChange(password: String) {
        this.password = password;
    }

    var shouldShowDialog by mutableStateOf(false)

    fun signIn(navigateOnSuccess: () -> Unit) {
        authRepository.signIn(username, password) { success ->
            if (success) {
                navigateOnSuccess()
            } else {
                shouldShowDialog = true
            }
        }
    }
}