package com.example.job_app.feature_home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.job_app.feature_home.viewmodel.HomeViewModel

@Composable
fun ApplicationInfoScreen(navigateToProfileScreen: () -> Unit) {
    val homeViewModel: HomeViewModel = viewModel()
    Column(modifier = Modifier.fillMaxWidth()) {
        TopNavigationBar {navigateToProfileScreen()}
        Text(text = "Info")
    }
}