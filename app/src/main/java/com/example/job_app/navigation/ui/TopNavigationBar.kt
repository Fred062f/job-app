package com.example.job_app.navigation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.job_app.feature_home.ui.ConfirmDialogComponent
import com.example.job_app.feature_home.viewmodel.HomeViewModel

// Frederik

@Composable
fun TopNavigationBar(
    navigateToLoginScreen: () -> Unit)
{
    val homeViewModel: HomeViewModel = viewModel()

    if (homeViewModel.logoutDialog) {
        ConfirmDialogComponent(
            title = "Log ud",
            text = "Er du sikker på at du vil logge ud af din konto?",
            onConfirm = {
                navigateToLoginScreen()
            }
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Placeholder icon
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = Color.DarkGray,
            modifier = Modifier
                .size(55.dp)
                .padding(8.dp)
                .clickable(onClick = { })
                .alpha(0f)
        )
        Text(text = "AnsøgNemt", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color(46, 90, 186), modifier = Modifier.padding(10.dp))
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
            contentDescription = "Profile",
            tint = Color.DarkGray,
            modifier = Modifier
                .size(55.dp)
                .padding(8.dp)
                .clickable(onClick = { homeViewModel.logoutDialog = true })
        )
    }
}

@Composable
fun AlternativeTopNavigationBar(
    navigateToLoginScreen: () -> Unit,
    navigateBack: () -> Unit) {

    val homeViewModel: HomeViewModel = viewModel()

    if (homeViewModel.logoutDialog) {
        ConfirmDialogComponent(
            title = "Log ud",
            text = "Er du sikker på at du vil logge ud af din konto?",
            onConfirm = {
                navigateToLoginScreen()
            }
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = Color.DarkGray,
            modifier = Modifier
                .size(55.dp)
                .padding(8.dp)
                .clickable(onClick = { navigateBack() })
        )
        Text(text = "AnsøgNemt", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color(46, 90, 186), modifier = Modifier.padding(10.dp))
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
            contentDescription = "Logout",
            tint = Color.DarkGray,
            modifier = Modifier
                .size(55.dp)
                .padding(8.dp)
                .clickable(onClick = { homeViewModel.logoutDialog = true })
        )
    }
}