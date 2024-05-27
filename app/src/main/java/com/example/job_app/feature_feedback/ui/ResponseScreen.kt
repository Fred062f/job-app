package com.example.job_app.feature_feedback.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.job_app.feature_home.ui.AlternativeTopNavigationBar
import com.example.job_app.navigation.ui.BottomNavigationBar
// Frederik
@Composable
fun ResponseScreen(responseMessage: String, navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        AlternativeTopNavigationBar(
            navigateToLoginScreen = { navController.navigate("login") },
            navigateBack = {navController.popBackStack()}
        )
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(615.dp))
        {
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceContainer)
                .padding(10.dp)) {
                item {
                    Text(
                        text = responseMessage,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomEnd
    ) {
        BottomNavigationBar(navController)
    }
}