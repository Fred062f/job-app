package com.example.job_app.feature_auth.repository

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.example.job_app.feature_application_form.viewmodel.applicationClass
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class AuthRepository {
    // Initialize Firebase Auth
    private var auth: FirebaseAuth = Firebase.auth


    fun userIsAuthorized(): Boolean {
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        return currentUser != null
    }

    fun getCurrentUser(): FirebaseUser? {
        return Firebase.auth.currentUser
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

    fun signIn(email: String, password: String, onResult: (Boolean) -> Unit) {
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

