package com.example.job_app.feature_feedback.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.job_app.feature_feedback.viewmodel.ChatViewModel
import com.example.job_app.feature_home.ui.AlternativeTopNavigationBar
import com.example.job_app.navigation.ui.BottomNavigationBar

@Composable
fun RequestScreen(navController: NavController) {
    val chatViewModel: ChatViewModel = viewModel()

    var loading by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        AlternativeTopNavigationBar(
            navigateToLoginScreen = { navController.navigate("login") },
            navigateBack = { navController.navigate("home") }
        )

        if (loading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        } else {
            TextField(
                value = chatViewModel.application,
                onValueChange = { chatViewModel.onApplicationChange(it) },
                placeholder = { Text("Indsæt din ansøgning her") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(565.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopEnd) {
                Button(onClick = {
                    chatViewModel.createChatCompletion(navController)
                    loading = true }, enabled = !loading)
                {
                    Text("Få feedback")
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

@Preview(showBackground = true)
@Composable
fun RequestScreenPreview() {
    RequestScreen(navController = rememberNavController())
}