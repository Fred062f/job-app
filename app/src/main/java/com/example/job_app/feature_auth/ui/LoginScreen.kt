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
import com.example.job_app.feature_auth.viewmodel.LoginViewModel
import com.example.job_app.ui.theme.JobappTheme

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    navigateToRegisterScreen: () -> Unit
) {
    val loginViewModel: LoginViewModel = viewModel()
    if (loginViewModel.shouldShowDialog) {
        AlertDialog(text = "Login failure")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = loginViewModel.username,
            onValueChange = { loginViewModel.onUsernameChange(it) },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = loginViewModel.password,
            onValueChange = { loginViewModel.onPasswordChange(it) },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Button(onClick = { loginViewModel.signIn(onLoginSuccess) }) {
            Text("Login")
        }
        Text(
            text = "Don't have an account? Click here",
            Modifier.clickable { navigateToRegisterScreen() }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    JobappTheme {
        LoginScreen(onLoginSuccess = {}, navigateToRegisterScreen = {})
    }
}