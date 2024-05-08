package com.example.job_app.feature_application.feature_application.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.job_app.feature_application.feature_application.viewmodel.ApplicationViewModel
import com.example.job_app.feature_home.ui.AlternativeTopNavigationBar
import com.example.job_app.feature_home.viewmodel.HomeViewModel
import com.example.job_app.ui.theme.JobappTheme

@Composable
fun ApplicationScreen(
    id: String,
    userIsNotAuthorized: () -> Unit,
    navigateToLoginScreen: () -> Unit,
    navigateBack: () -> Unit
) {
    val applicationViewModel: ApplicationViewModel = viewModel()
    val homeViewModel: HomeViewModel = viewModel()
    // If user is not authorized navigate to login screen
    if (!homeViewModel.userIsAuthorized()) return userIsNotAuthorized()

    applicationViewModel.initialize(id)

    val application = applicationViewModel.jobApplication.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AlternativeTopNavigationBar(navigateToLoginScreen, navigateBack)

        if (application != null) {
            Text("Id: ${application.id}")
            Text("Job titel: ${application.jobTitle}")
            Text("Status: ${application.status}")
            Text("Timestamp: ${application.timestamp}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ApplicationScreenPreview() {
    JobappTheme {
        ApplicationScreen(id = "", userIsNotAuthorized = {}, navigateToLoginScreen = {}, navigateBack = {})
    }
}