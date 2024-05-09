package com.example.job_app.feature_auth.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.job_app.feature_auth.viewmodel.RegisterViewModel
import com.example.job_app.ui.theme.JobappTheme

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    navigateToLoginScreen: () -> Unit) {
    val registerViewModel: RegisterViewModel = viewModel()
    if (registerViewModel.shouldShowDialog) {
        AlertDialog(title = "Fejl", text = "Der skete en fejl ved oprettelse af bruger")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = registerViewModel.email,
            onValueChange = { registerViewModel.onEmailChange(it) },
            label = { Text("E-mail") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = registerViewModel.password,
            onValueChange = { registerViewModel.onPasswordChange(it) },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Button(onClick = { registerViewModel.createAccount(onRegisterSuccess) }) {
            Text("Opret konto")
        }
        Text(
            text = "Har du allerede en konto? Klik her.",
            Modifier.clickable { navigateToLoginScreen() }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    JobappTheme {
        RegisterScreen(onRegisterSuccess = {}, navigateToLoginScreen = {})
    }
}