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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.job_app.feature_application_form.viewmodel.ApplicationFormViewModel
import com.example.job_app.feature_auth.ui.AlertDialog
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

    if (applicationFormViewModel.shouldShowDialogOnJobTitleError) {
        AlertDialog(title = "Fejl", text = "Du skal indtaste en jobtitel")
    }
    if (applicationFormViewModel.shouldShowDialogOnDateError) {
        AlertDialog(title = "Fejl", text = "Datoen er ikke gyldig")
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
                if (applicationFormViewModel.jobTitle.isEmpty()) {
                    applicationFormViewModel.shouldShowDialogOnJobTitleError = true
                    return@Button
                } else {
                    try {
                        applicationFormViewModel.addJobApplicationToList(
                            JobApplication(
                                jobTitle = applicationFormViewModel.jobTitle,
                                status = false,
                                timestamp = applicationFormViewModel.convertDateStringToTimestamp(),
                                description = applicationFormViewModel.description
                            ),
                            userId = homeViewModel.getCurrentUser()?.uid.toString(),
                            navigateBack = navigateBack
                        )
                    } catch (e: Exception) {
                        Log.e("JobApplicationForm", "Error adding job application to list", e)
                        applicationFormViewModel.shouldShowDialogOnDateError = true
                        return@Button
                    }
                }
            }
                , modifier = Modifier
                    .align(Alignment.Center)
                    .size(width = 200.dp, height = 50.dp)
            ) {
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
