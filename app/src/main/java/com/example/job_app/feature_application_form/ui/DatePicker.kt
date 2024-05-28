package com.example.job_app.feature_application_form.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.job_app.feature_application_form.viewmodel.ApplicationFormViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Victor + Frederik
// Konverterer millisekunder til en dato streng
private fun convertMillisToDateString(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}
// Frederik
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(
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
    } ?: "Klik her for at vælge dato"

    androidx.compose.material3.DatePickerDialog(
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
        androidx.compose.material3.DatePicker(state = datePickerState)
    }
}

@Composable
fun DatePickerDialog() {
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
        DatePicker(
            onDateSelected = { applicationFormViewModel.date = it },
            onDismiss = { showDatePicker = false }
        )
    }
}