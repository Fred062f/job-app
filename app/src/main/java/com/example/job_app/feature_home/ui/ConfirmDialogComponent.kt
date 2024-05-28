package com.example.job_app.feature_home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.job_app.feature_home.viewmodel.HomeViewModel
import com.example.job_app.ui.theme.JobappTheme
// Frederik
@Composable
fun ConfirmDialogComponent(title: String, text: String, onConfirm: () -> Unit) {
    val homeViewModel: HomeViewModel = viewModel()
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) { // 2
        androidx.compose.material3.AlertDialog( // 3
            onDismissRequest = { // 4
                openDialog.value = false
                homeViewModel.shouldShowDialog = false
                homeViewModel.logoutDialog = false
            },
            // 5
            title = { Text(title) },
            text = { Text(text) },
            confirmButton = { // 6
                Button(
                    onClick = {
                        onConfirm()
                        openDialog.value = false
                        homeViewModel.shouldShowDialog = false
                        homeViewModel.logoutDialog = false
                    }
                ) {
                    Text(
                        text = "Ja",
                        color = Color.White
                    )
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        openDialog.value = false
                        homeViewModel.shouldShowDialog = false
                        homeViewModel.logoutDialog = false
                    }
                ) {
                    Text("Nej")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ConfirmDialogComponentPreview() {
    JobappTheme {
        Column {
            ConfirmDialogComponent(title = "Error", text = "Text", onConfirm = {})
        }
    }
}