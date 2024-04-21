package com.example.job_app

import androidx.lifecycle.ViewModel

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