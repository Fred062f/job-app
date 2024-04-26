package com.example.job_app.feature_auth.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.job_app.feature_auth.viewmodel.RegisterViewModel
import com.example.job_app.ui.theme.JobappTheme

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    navigateToLoginScreen: () -> Unit) {
    val registerViewModel: RegisterViewModel = viewModel()

    if (registerViewModel.shouldShowDialog) {
        AlertDialog(text = "Register failure")
    }

    Box(contentAlignment = Alignment.TopStart, modifier = Modifier.fillMaxWidth()) {
        IconButton(
            onClick = {
                navigateToLoginScreen()
            },
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Icon(Icons.Filled.ArrowBack, "Back", tint = Color.Black)
        }
        }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {Spacer(modifier = Modifier.height(44.dp))
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
            Text("Create account")
        }
        Text(
            text = "Already have an account? Click here",
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