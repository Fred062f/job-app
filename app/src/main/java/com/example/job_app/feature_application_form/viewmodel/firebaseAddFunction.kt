package com.example.job_app.feature_application_form.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.navigation.NavController
import com.example.job_app.feature_home.viewmodel.HomeViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

fun addApplication(jobApplication: applicationClass, userId: String, navigateBack: () -> Unit, viewModel: HomeViewModel) {
    val db = Firebase.firestore
    db.collection("users").document(userId).collection("jobApplications")
        .add(jobApplication)
        .addOnSuccessListener { documentReference ->
            Log.d(
                ContentValues.TAG,"DocumentSnapshot added with ID: ${documentReference.id}"
            )
            // Trigger recomposition
            viewModel.recompose(userId)
            navigateBack()
        }
        .addOnFailureListener { e ->
            Log.w(
                ContentValues.TAG, "Error adding document", e
            )
        }
}