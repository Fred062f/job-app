package com.example.job_app.feature_application_form.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.job_app.feature_application_form.viewmodel.ApplicationFormViewModel
import com.example.job_app.feature_home.models.JobApplication
import com.example.job_app.feature_home.ui.AlternativeTopNavigationBar
import com.example.job_app.feature_home.viewmodel.HomeViewModel
import com.example.job_app.ui.theme.JobappTheme


@Composable
fun ApplicationFormScreen(
    navigateToLoginScreen: () -> Unit,
    userIsNotAuthorized: () -> Unit,
    navigateBack: () -> Unit
) {
    var coverDate by remember { mutableStateOf("") }
    var coverTime by remember { mutableStateOf("") }

    val applicationFormViewModel: ApplicationFormViewModel = viewModel()
    val homeViewModel: HomeViewModel = viewModel()
    if (!homeViewModel.userIsAuthorized()) return userIsNotAuthorized()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
        AlternativeTopNavigationBar(
            navigateToLoginScreen = { homeViewModel.signOut(navigateToLoginScreen) },
            navigateBack = navigateBack
        )
        Spacer(modifier = Modifier.size(32.dp))
        Row {
            Text(text = "Step 1: Indtast information", fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.size(32.dp))
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Jobtitel:      ")
            TextField(
                value = applicationFormViewModel.jobTitle,
                onValueChange = { applicationFormViewModel.onJobTitleChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.size(32.dp))
        Row {

            Text(text = "Step 2: Indtast frist for ansøgelse", fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.size(24.dp))
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Dato:          ")
            TextField(
                value = coverDate,
                onValueChange = { coverDate = it },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.size(12.dp))
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Tidspunkt: ")
            TextField(
                value = coverTime,
                onValueChange = { coverTime = it },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.size(12.dp))
        Spacer(modifier = Modifier.size(24.dp))
        Button(onClick = {
            applicationFormViewModel.addJobApplicationToList(
                JobApplication(
                    jobTitle = applicationFormViewModel.jobTitle,
                    status = false,
                    timestamp = null
                ),
                userId = homeViewModel.getCurrentUser()?.uid.toString(),
                navigateBack = navigateBack
            )
        }) {
            Text(text = "Opret Ansøgning")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppFormPreview() {
    JobappTheme {
        //ApplicationFormScreen()
    }
}