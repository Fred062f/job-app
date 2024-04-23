package com.example.job_app.feature_home.viewmodel

import androidx.lifecycle.ViewModel
import com.example.job_app.feature_auth.repository.AuthRepository

class HomeViewModel : ViewModel() {
    private val authRepository: AuthRepository = AuthRepository();

    fun userIsAuthorized(): Boolean {
        return authRepository.userIsAuthorized()
    }
    fun getCurrentUser(): String? {
        return authRepository.getCurrentUser()
    }
    fun signOut(navigateOnSuccess: () -> Unit) {
        authRepository.signOut(navigateOnSuccess)
    }
}