package com.example.job_app.feature_home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.job_app.feature_home.models.JobApplication
import com.example.job_app.feature_home.viewmodel.HomeViewModel
import com.example.job_app.ui.theme.JobappTheme

@Composable
fun HomeScreen(
    navigateToProfileScreen: () -> Unit,
    navigateToApplicationInfoScreen: () -> Unit,
    userIsNotAuthorized: () -> Unit
) {
    val homeViewModel: HomeViewModel = viewModel()
    if (!homeViewModel.userIsAuthorized()) return userIsNotAuthorized()
    val getData = homeViewModel.state.value
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)) {
        TopNavigationBar {navigateToProfileScreen()}
        Spacer(modifier = Modifier.height(25.dp))
        DateHeader()
        Spacer(modifier = Modifier.height(16.dp))
        ApplicationDeadlines()
        if (getData.size == 0) {
            Text(text = "Ingen kommende ansøgningsfrister")
        }
        ItemList(items = getData, navigateToApplicationInfoScreen)
        BottomFloatingActionButton {}
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    JobappTheme {
        HomeScreen(navigateToProfileScreen = {}, navigateToApplicationInfoScreen = {}, userIsNotAuthorized = {})
    }
}