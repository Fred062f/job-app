package com.example.job_app.feature_profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.job_app.feature_home.ui.HomeScreen
import com.example.job_app.feature_home.viewmodel.HomeViewModel
import com.example.job_app.ui.theme.JobappTheme

@Composable
fun ProfileScreen(
    navigateOnSuccess: () -> Unit,
    userIsNotAuthorized: () -> Unit
) {
    val homeViewModel: HomeViewModel = viewModel()
    // If user is not authorized navigate to login screen
    if (!homeViewModel.userIsAuthorized()) return userIsNotAuthorized()
    val email = homeViewModel.getCurrentUser()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = res.drawable.ic_launcher_background.xml, contentDescription = "Logo")

        Text("Logged in as $email")

        Button(onClick = { homeViewModel.signOut(navigateOnSuccess) }) {
            Text("Logout")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    JobappTheme {
        HomeScreen(navigateOnSuccess = {}, userIsNotAuthorized = {})
    }
}