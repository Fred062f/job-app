package com.example.job_app.feature_application_form.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.job_app.MainActivity
import com.example.job_app.feature_application_form.viewmodel.ApplicationFormViewModel
import com.example.job_app.feature_application_form.viewmodel.ApplicationFormViewModelFactory
import com.example.job_app.feature_application_form.viewmodel.NotificationScheduler
import com.example.job_app.feature_home.models.JobApplication
import com.example.job_app.feature_home.viewmodel.HomeViewModel
import com.example.job_app.navigation.ui.AlternativeTopNavigationBar
import com.example.job_app.navigation.ui.BottomNavigationBar
import com.example.job_app.util.NotificationHelper

// Frederik + Jonathan
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationFormScreen(
    navigateToLoginScreen: () -> Unit,
    userIsNotAuthorized: () -> Unit,
    navigateBack: () -> Unit,
    navController: NavController
) {
    val context = LocalContext.current
    val activity = context as MainActivity

    NotificationHelper.createNotificationChannel(context)
    val viewModelFactory = ApplicationFormViewModelFactory(notificationScheduler = NotificationScheduler(context))
    val applicationFormViewModel: ApplicationFormViewModel = viewModel(factory = viewModelFactory)
    val homeViewModel: HomeViewModel = viewModel()
    if (!homeViewModel.userIsAuthorized()) return userIsNotAuthorized()

    if (applicationFormViewModel.shouldShowDialogOnJobTitleError) {
        FormAlertDialog(title = "Fejl", text = "Du skal indtaste en jobtitel")
    }
    if (applicationFormViewModel.shouldShowDialogOnDateError) {
        FormAlertDialog(title = "Fejl", text = "Datoen er ikke gyldig")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
        AlternativeTopNavigationBar(
            navigateToLoginScreen = { homeViewModel.signOut(navigateToLoginScreen) },
            navigateBack = navigateBack
        )
        Spacer(modifier = Modifier.size(32.dp))

        Text(text = "Step 1: Indtast information", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.size(32.dp))

        Text(text = "Jobtitel:")
        TextField(
            value = applicationFormViewModel.jobTitle,
            onValueChange = { applicationFormViewModel.onJobTitleChange(it) },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Indtast jobtitel") }
        )

        Spacer(modifier = Modifier.size(32.dp))

        Text(text = "Beskrivelse (Valgfrit):")
        TextField(
            value = applicationFormViewModel.description,
            onValueChange = { applicationFormViewModel.onDescriptionChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            placeholder = { Text("Tilføj en beskrivelse af jobbet") }
        )

        Spacer(modifier = Modifier.size(32.dp))

        Text(text = "Step 2: Indtast frist for ansøgelse", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.size(24.dp))

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Dato:")
            Spacer(modifier = Modifier.size(10.dp))
            DatePickerDialog()
        }

      Spacer(modifier = Modifier.size(30.dp))

      Box(modifier = Modifier.fillMaxWidth().size(width = 200.dp, height = 50.dp), contentAlignment = Alignment.Center) {
        Button(onClick = {
            if (applicationFormViewModel.jobTitle.isEmpty()) {
                applicationFormViewModel.shouldShowDialogOnJobTitleError = true
                return@Button
            } else {
                try {
                    val jobApplication = JobApplication(
                        jobTitle = applicationFormViewModel.jobTitle,
                        status = false,
                        timestamp = applicationFormViewModel.convertDateStringToTimestamp(),
                        description = applicationFormViewModel.description
                    )

                    activity.scheduleNotificationWithPermissionCheck(jobApplication)

                    applicationFormViewModel.addJobApplicationToList(
                        jobApplication = jobApplication,
                        userId = homeViewModel.getCurrentUser()?.uid.toString(),
                        navigateBack = navigateBack
                    )
                } catch (e: Exception) {
                    Log.e("JobApplicationForm", "Error adding job application to list", e)
                    applicationFormViewModel.shouldShowDialogOnDateError = true
                    return@Button
                }
            }
        }) {
            Text("Opret min ansøgning")
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