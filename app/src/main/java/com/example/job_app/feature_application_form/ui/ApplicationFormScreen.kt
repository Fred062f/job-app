package com.example.job_app.feature_application_form.ui

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.job_app.feature_application_form.viewmodel.ApplicationFormViewModel
import com.example.job_app.feature_application_form.viewmodel.ApplicationFormViewModelFactory
import com.example.job_app.feature_application_form.viewmodel.NotificationScheduler
import com.example.job_app.feature_home.models.JobApplication
import com.example.job_app.feature_home.repository.FirestoreRepository
import com.example.job_app.feature_home.ui.AlternativeTopNavigationBar
import com.example.job_app.feature_home.viewmodel.HomeViewModel
import com.example.job_app.util.NotificationHelper
import java.text.SimpleDateFormat
import java.util.Date


@Composable
fun ApplicationFormScreen(
    firestoreRepository: FirestoreRepository,
    notificationScheduler: NotificationScheduler,
    navigateToLoginScreen: () -> Unit,
    userIsNotAuthorized: () -> Unit,
    navigateBack: () -> Unit,
    context: Context = LocalContext.current
) {

    val viewModelFactory = ApplicationFormViewModelFactory(firestoreRepository, notificationScheduler)
    val applicationFormViewModel: ApplicationFormViewModel = viewModel(factory = viewModelFactory)
    val homeViewModel: HomeViewModel = viewModel()
    if (!homeViewModel.userIsAuthorized()) return userIsNotAuthorized()

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
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = { Text("Indtast jobtitel") }
        )

        Spacer(modifier = Modifier.size(32.dp))

        Text(text = "Beskrivelse (Valgfrit):")
        TextField(
            value = applicationFormViewModel.description,
            onValueChange = {  applicationFormViewModel.onDescriptionChange(it) },
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
            MyDatePickerDialog()
        }

        Spacer(modifier = Modifier.size(65.dp))

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomCenter) {
            Button(onClick = {
                val jobApplication = JobApplication(
                    jobTitle = applicationFormViewModel.jobTitle,
                    status = false,
                    timestamp = applicationFormViewModel.convertDateStringToTimestamp()
                )
                applicationFormViewModel.addJobApplicationToList(
                    jobApplication,
                    homeViewModel.getCurrentUser()?.uid.toString(),
                    navigateBack
                )

                // Schedule the notification - Missing testing
                jobApplication.timestamp?.let { timestamp ->
                    val currentTimeMillis = System.currentTimeMillis()
                    val notificationTimeMillis = timestamp.seconds * 1000 - 24 * 3600 * 1000 // 24 hours before the due date
                    if (notificationTimeMillis > currentTimeMillis) { // Ensure the notification time is in the future
                        NotificationHelper.scheduleNotification(
                            context,
                            3,
                            "Application Reminder",
                            "Your application deadline for ${jobApplication.jobTitle} is approaching!",  // Content
                        )
                    }
                }
            }) {
                Text(text = "Opret ansøgning")
            }
        }
        }
    }


private fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    return formatter.format(Date(millis))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDatePickerDialog(
    onDateSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState(selectableDates = object : SelectableDates {
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            return utcTimeMillis >= System.currentTimeMillis()
        }
    })

    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    DatePickerDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(onClick = {
                onDateSelected(selectedDate)
                onDismiss()
            }

            ) {
                Text(text = "OK")
            }
        },
        dismissButton = {
            Button(onClick = {
                onDismiss()
            }) {
                Text(text = "Luk")
            }
        }
    ) {
        DatePicker(
            state = datePickerState
        )
    }
}

@Composable
fun MyDatePickerDialog() {
    val applicationFormViewModel: ApplicationFormViewModel = viewModel()

    var showDatePicker by remember {
        mutableStateOf(false)
    }

    Box(contentAlignment = Alignment.Center) {
        TextButton(onClick = {  showDatePicker = true }) {
            Text(text = applicationFormViewModel.date)
        }
    }


    if (showDatePicker) {
        MyDatePickerDialog(
            onDateSelected = { applicationFormViewModel.date = it },
            onDismiss = { showDatePicker = false }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppFormPreview() {
    MyDatePickerDialog()
}
