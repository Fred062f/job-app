package com.example.job_app.feature_application_form.viewmodel

import androidx.lifecycle.ViewModel
import com.example.job_app.feature_auth.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser

class ApplicationViewModel: ViewModel() {
    private val authRepository: AuthRepository = AuthRepository();

    fun userIsAuthorized(): Boolean {
        return authRepository.userIsAuthorized()
    }
    fun getCurrentUser(): FirebaseUser? {
        return authRepository.getCurrentUser()
    }
    fun signOut(navigateOnSuccess: () -> Unit) {
        authRepository.signOut(navigateOnSuccess)
    }

}