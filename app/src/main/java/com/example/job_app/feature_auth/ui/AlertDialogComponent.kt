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
import com.example.job_app.ui.theme.JobappTheme

@Composable
fun AlertDialog(text: String) {
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) { // 2
        AlertDialog( // 3
            onDismissRequest = { // 4
                openDialog.value = false
            },
            // 5
            title = { Text(text = "Error") },
            text = { Text(text) },
            confirmButton = { // 6
                Button(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text(
                        text = "Confirm",
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
            AlertDialog("Please try again")
        }
    }
}

