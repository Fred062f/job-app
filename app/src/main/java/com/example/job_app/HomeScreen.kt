package com.example.job_app

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
import com.example.job_app.ui.theme.JobappTheme

@Composable
fun HomeScreen(
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
        Text("Logged in as $email")

        Button(onClick = { homeViewModel.signOut(navigateOnSuccess) }) {
            Text("Logout")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    JobappTheme {
        HomeScreen(navigateOnSuccess = {}, userIsNotAuthorized = {})
    }
}