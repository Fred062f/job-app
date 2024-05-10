package com.example.job_app.feature_auth.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.job_app.feature_application_form.viewmodel.ApplicationFormViewModel
import com.example.job_app.ui.theme.JobappTheme

@Composable
fun AlertDialog(title: String, text: String) {
    val applicationFormViewModel: ApplicationFormViewModel = viewModel()
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) { // 2
        AlertDialog( // 3
            onDismissRequest = { // 4
                openDialog.value = false
                applicationFormViewModel.shouldShowDialogOnJobTitleError = false
                applicationFormViewModel.shouldShowDialogOnDateError = false
            },
            // 5
            title = { Text(title) },
            text = { Text(text) },
            confirmButton = { // 6
                Button(
                    onClick = {
                        openDialog.value = false
                        applicationFormViewModel.shouldShowDialogOnJobTitleError = false
                        applicationFormViewModel.shouldShowDialogOnDateError = false
                    }
                ) {
                    Text(
                        text = "OK",
                        color = Color.White
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AlertDialogPreview() {
    JobappTheme {
        Column {
            AlertDialog(title = "Error", text = "Text")
        }
    }
}

