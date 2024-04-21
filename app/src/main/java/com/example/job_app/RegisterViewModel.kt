package com.example.job_app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {
    private val authRepository: AuthRepository = AuthRepository();

    var email by mutableStateOf("")

    var password by mutableStateOf("")

    fun onEmailChange(email: String) {
        this.email = email;
    }

    fun onPasswordChange(password: String) {
        this.password = password;
    }

    var shouldShowDialog by mutableStateOf(false)

    fun createAccount(navigateOnSuccess: () -> Unit) {
        authRepository.createAccount(email, password) {success ->
            if (success) {
                navigateOnSuccess()
            } else {
                shouldShowDialog = true
            }
        }
    }
}