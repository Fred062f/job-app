package com.example.job_app.feature_home.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.job_app.feature_home.models.JobApplication
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import java.security.Timestamp

class FirestoreRepository {
    val db = Firebase.firestore
    suspend fun getDataFromFirestore(userId: String):MutableList<JobApplication> {
        val jobApplications: MutableList<JobApplication> = mutableListOf()
        try {
            db.collection("users").document(userId).collection("jobApplications").get().await().map {
                val result = it.toObject(JobApplication::class.java)
                jobApplications.add(result)
            }
        } catch (e: FirebaseFirestoreException) {
            Log.d(TAG, "getDataFromFirestore: $e")
        }
        return jobApplications
    }

}