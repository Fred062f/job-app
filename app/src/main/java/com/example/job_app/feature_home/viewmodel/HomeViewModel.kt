package com.example.job_app.feature_home.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.job_app.feature_auth.repository.AuthRepository
import com.example.job_app.feature_home.models.JobApplication
import com.example.job_app.feature_home.repository.FirestoreRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
// Frederik
class HomeViewModel : ViewModel() {
    private val authRepository: AuthRepository = AuthRepository();
    private val firestoreRepository: FirestoreRepository = FirestoreRepository()

    fun userIsAuthorized(): Boolean {
        return authRepository.userIsAuthorized()
    }
    fun getCurrentUser(): FirebaseUser? {
        return authRepository.getCurrentUser()
    }
    fun signOut(navigateOnSuccess: () -> Unit) {
        authRepository.signOut(navigateOnSuccess)
    }

    var logoutDialog by mutableStateOf(false)

    var shouldShowDialog by mutableStateOf(false)

    var itemId by mutableStateOf("")


    @SuppressLint("MutableCollectionMutableState")
    var state = mutableStateOf(mutableListOf(JobApplication()))

    init {
        getData(authRepository.getCurrentUser()?.uid.toString())
    }

    fun getData(userId: String) {
        viewModelScope.launch {
            state.value = firestoreRepository.getDataFromFirestore(userId)
        }
    }

    fun deleteData(userId: String, jobApplicationId: String) {
        viewModelScope.launch {
            firestoreRepository.deleteDataFromFirestore(userId, jobApplicationId)
            state.value = firestoreRepository.getDataFromFirestore(userId)
        }
    }

    fun updateData(userId: String, jobApplicationId: String, currentStatus: Boolean) {
        viewModelScope.launch {
            firestoreRepository.updateApplicationStatus(userId, jobApplicationId, currentStatus)
            state.value = firestoreRepository.getDataFromFirestore(userId)
        }
    }
}