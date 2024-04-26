package com.example.job_app.feature_home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.job_app.feature_home.models.JobApplication

@Composable
fun ApplicationInfoScreen(
    item: String?,
    navigateToProfileScreen: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TopNavigationBar {navigateToProfileScreen()}
        if (item != null) {
            Text(text = item)
        }
    }
}