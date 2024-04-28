package com.example.job_app.feature_home.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.job_app.feature_home.models.JobApplication
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class FirestoreRepository {
    val db = Firebase.firestore
    suspend fun getDataFromFirestore(userId: String):MutableList<JobApplication> {
        val jobApplications: MutableList<JobApplication> = mutableListOf()
        try {
            db.collection("users").document(userId).collection("jobApplications").get().await().map {
                println(it.id)
                val result = JobApplication(
                    id = it.id,
                    jobTitle = it.getString("jobTitle"),
                    status = it.getBoolean("status"),
                    timestamp = it.getTimestamp("timestamp")
                )
                //val result = it.toObject(JobApplication::class.java)
                jobApplications.add(result)
            }
        } catch (e: FirebaseFirestoreException) {
            Log.d(TAG, "getDataFromFirestore: $e")
        }
        return jobApplications
    }
    fun deleteDataFromFirestore(userId: String, jobApplicationId: String) {
        db.collection("users").document(userId).collection("jobApplications").document(jobApplicationId)
            .delete()
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot successfully deleted!")

            }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
    }

    fun updateApplicationStatus(userId: String, jobApplicationId: String, currentStatus: Boolean) {
        db.collection("users").document(userId).collection("jobApplications").document(jobApplicationId)
            .update("status", currentStatus.not())
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot successfully updated!")

            }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }

}