package com.example.job_app.feature_home.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.job_app.feature_auth.repository.AuthRepository
import com.example.job_app.feature_home.models.JobApplication
import com.example.job_app.feature_home.repository.FirestoreRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val authRepository: AuthRepository = AuthRepository();
    private val firestoreRepository: FirestoreRepository = FirestoreRepository()

    fun userIsAuthorized(): Boolean {
        return authRepository.userIsAuthorized()
    }
    fun getCurrentUser(): String? {
        return authRepository.getCurrentUser()
    }
    fun signOut(navigateOnSuccess: () -> Unit) {
        authRepository.signOut(navigateOnSuccess)
    }

    @SuppressLint("MutableCollectionMutableState")
    var state = mutableStateOf(mutableListOf(JobApplication()))

    init {
        getData(authRepository.getCurrentUser()?.uid.toString())
    }

    private fun getData(userId: String) {
        viewModelScope.launch {
            state.value = firestoreRepository.getDataFromFirestore(userId)
        }
    }

}