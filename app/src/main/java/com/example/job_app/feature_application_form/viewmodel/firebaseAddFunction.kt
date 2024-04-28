package com.example.job_app.feature_application_form.viewmodel

import android.content.ContentValues
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

fun addApplication(jobApplication: applicationClass) {
    val db = Firebase.firestore
    db.collection("jobApplications")
        .add(jobApplication)
        .addOnSuccessListener { documentReference ->
            Log.d(
                ContentValues.TAG,"DocumentSnapshot added with ID: ${documentReference.id}"
            )
        }
        .addOnFailureListener { e ->
            Log.w(
                ContentValues.TAG, "Error adding document", e
            )
        }

}