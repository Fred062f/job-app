package com.example.job_app.feature_application_form.ui

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationFormScreen(
    firestoreRepository: FirestoreRepository,
    notificationScheduler: NotificationScheduler,
    navigateToLoginScreen: () -> Unit,
    userIsNotAuthorized: () -> Unit,
    navigateBack: () -> Unit
) {
    val context = LocalContext.current
    NotificationHelper.createNotificationChannel(context)

    val viewModelFactory = ApplicationFormViewModelFactory(firestoreRepository, notificationScheduler)
    val applicationFormViewModel: ApplicationFormViewModel = viewModel(factory = viewModelFactory)
    val homeViewModel: HomeViewModel = viewModel()

    if (!homeViewModel.userIsAuthorized()) return userIsNotAuthorized()

    var showDatePicker by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
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
            TextButton(onClick = { showDatePicker = true }) {
                Text(text = applicationFormViewModel.date)
            }
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
            }) {
                Text(text = "Opret ansøgning")
            }
        }

        if (showDatePicker) {
            MyDatePickerDialog(
                onDateSelected = { selectedDate ->
                    applicationFormViewModel.date = selectedDate
                    showDatePicker = false
                },
                onDismiss = { showDatePicker = false }
            )
        }}}
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
        convertMillisToDateString(it)
    } ?: ""

    DatePickerDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(onClick = {
                onDateSelected(selectedDate)
                onDismiss()
            }) {
                Text(text = "OK")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text(text = "Luk")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

private fun convertMillisToDateString(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}


@Preview(showBackground = true)
@Composable
fun AppFormPreview() {
    MyDatePickerDialog(onDateSelected = {}, onDismiss = {})
}
