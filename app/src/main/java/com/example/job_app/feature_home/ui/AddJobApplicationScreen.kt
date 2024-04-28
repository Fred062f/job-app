package com.example.job_app.feature_home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.job_app.feature_home.viewmodel.HomeViewModel

@Composable
fun AddJobApplicationScreen(
    navigateToLoginScreen: () -> Unit,
    userIsNotAuthorized: () -> Unit,
    navigateBack: () -> Unit
) {
    val homeViewModel: HomeViewModel = viewModel()
    if (!homeViewModel.userIsAuthorized()) return userIsNotAuthorized()
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        AlternativeTopNavigationBar(
            navigateToLoginScreen = { homeViewModel.signOut(navigateToLoginScreen) },
            navigateBack = navigateBack
        )
    }
}