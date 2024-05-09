package com.example.job_app.feature_home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.job_app.feature_auth.ui.AlertDialog
import com.example.job_app.feature_home.viewmodel.HomeViewModel
import com.example.job_app.ui.theme.JobappTheme

@Composable
fun HomeScreen(
    navigateToLoginScreen: () -> Unit,
    userIsNotAuthorized: () -> Unit,
    navigateToAddJobApplicationScreen: () -> Unit,
    navController: NavController
) {
    val homeViewModel: HomeViewModel = viewModel()
    if (!homeViewModel.userIsAuthorized()) return userIsNotAuthorized()
    // Triggers recomposition every time screen is rendered
    homeViewModel.getData(homeViewModel.getCurrentUser()?.uid.toString())
    val getData = homeViewModel.state.value
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        TopNavigationBar { homeViewModel.signOut { navigateToLoginScreen() }}
        Spacer(modifier = Modifier.size(22.dp))
        DateHeader()
        Spacer(modifier = Modifier.size(6.dp))
        ApplicationDeadlines()
        Spacer(modifier = Modifier.size(6.dp))
        HorizontalDivider()
        ListItemHeaders()
        HorizontalDivider()
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
        ){
            if (getData.size == 0) {
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                    Text(text = "Ingen kommende ansøgningsfrister", fontWeight = FontWeight.Bold)
                }
            }
            ListItem(getData, navController)
        }
        BottomFloatingActionButton { navigateToAddJobApplicationScreen() }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    JobappTheme {
        //HomeScreen(navigateToProfileScreen = {}, navigateToApplicationInfoScreen = {}, userIsNotAuthorized = {})
    }
}