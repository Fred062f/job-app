package com.example.job_app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class AuthRepository {
    // Initialwize Firebase Auth
    private var auth: FirebaseAuth = Firebase.auth

    fun userIsAuthorized(): Boolean {
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        return currentUser != null
    }

    fun getCurrentUser(): String? {
        return Firebase.auth.currentUser?.email
    }

    fun createAccount(email: String, password: String, onResult: (Boolean) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onResult(true)
            }
            .addOnFailureListener {
                onResult(false)
            }
    }

    fun signIn (email: String, password: String, onResult: (Boolean) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onResult(true)
            }
            .addOnFailureListener {
                onResult(false)
            }
    }

    fun signOut(navigateOnSignOut: () -> Unit) {
        auth.signOut()
        navigateOnSignOut()
    }
}