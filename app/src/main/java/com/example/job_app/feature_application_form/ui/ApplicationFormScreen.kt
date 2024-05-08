package com.example.job_app.feature_application_form.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.job_app.feature_application_form.viewmodel.ApplicationFormViewModel
import com.example.job_app.feature_home.models.JobApplication
import com.example.job_app.feature_home.ui.AlternativeTopNavigationBar
import com.example.job_app.feature_home.viewmodel.HomeViewModel
import java.text.SimpleDateFormat
import java.util.Date


@Composable
fun ApplicationFormScreen(
    navigateToLoginScreen: () -> Unit,
    userIsNotAuthorized: () -> Unit,
    navigateBack: () -> Unit
) {
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
            Text(text = "Dato:      ")
            MyDatePickerDialog(applicationFormViewModel)
        }
        Spacer(modifier = Modifier.size(24.dp))
        Button(onClick = {
            applicationFormViewModel.addJobApplicationToList(
                JobApplication(
                    jobTitle = applicationFormViewModel.jobTitle,
                    status = false,
                    timestamp = applicationFormViewModel.convertDateStringToTimestamp()
                ),
                userId = homeViewModel.getCurrentUser()?.uid.toString(),
                navigateBack = navigateBack
            )
        }) {
            Text(text = "Opret ansøgning")
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
    applicationFormViewModel: ApplicationFormViewModel,
    onDateSelected: (String) -> Unit,  // Callback for when a date is selected
    onDismiss: () -> Unit             // Callback for when the dialog is dismissed
) {
    val datePickerState = rememberDatePickerState(selectableDates = object : SelectableDates {
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            return utcTimeMillis >= System.currentTimeMillis()
        }
    })

    if (showDatePicker) {
        DatePickerDialog(
            state = datePickerState,  // Correct parameter name as expected by the component
            onDismissRequest = { onDismiss() },
            confirmButton = {
                Button(onClick = {
                    val selectedDate = datePickerState.selectedDateMillis?.let {
                        convertMillisToDate(it)
                    } ?: ""
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
        )
    }
}

@Composable
fun SomeParentComposable() {
    val viewModel: ApplicationFormViewModel = viewModel()  // Acquiring ViewModel instance

    MyDatePickerDialog(
        applicationFormViewModel = viewModel,
        onDateSelected = { selectedDate ->
            viewModel.date = selectedDate
        },
        onDismiss = {
            // Handle dismiss action
        }
    )
}



@Preview(showBackground = true)
@Composable
fun AppFormPreview() {
    MyDatePickerDialog()
}
