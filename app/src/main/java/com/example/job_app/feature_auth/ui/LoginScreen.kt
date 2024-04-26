package com.example.job_app.feature_auth.ui
import androidx.compose.material3.OutlinedTextField
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.job_app.feature_auth.viewmodel.LoginViewModel
import com.example.job_app.ui.theme.JobappTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    navigateToRegisterScreen: () -> Unit
    ) {
    val navController = rememberNavController()
    val loginViewModel: LoginViewModel = viewModel()
    if (loginViewModel.shouldShowDialog) {
        AlertDialog(text = "Login failure")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFF3F51B5)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.TopStart, modifier = Modifier.fillMaxWidth()) {
            IconButton(
                onClick = { /*navController.navigate("?Start screen??"TODO*/ },
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Icon(Icons.Filled.ArrowBack, "Back", tint = Color.Black)
            }
            Spacer(modifier = Modifier.height(312.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.White, CircleShape)
            ) {
                // Placeholder Logo
                Text("L",
                        style = MaterialTheme.typography.displayLarge.copy(color = Color (0xFF3F51B5)),
                        modifier = Modifier.align(Alignment.Center))
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
        OutlinedTextField(
            value = loginViewModel.username,
            onValueChange = { loginViewModel.onUsernameChange(it) },
            label = { Text("Username", color = if (loginViewModel.username.isEmpty()) Color.Gray else Color.White) },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent, RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedTextColor = Color.Transparent,
                unfocusedTextColor = Color.Transparent,
                cursorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.White,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White,
                containerColor = Color.Transparent
            )
                )



        TextField(
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
            "Forgot password?",
            modifier = Modifier.clickable { /* TODO: Forgot password action */ },
            color = Color.White,
            style = MaterialTheme.typography.displayLarge
        )
        Text(
            text = "Don't have an account? Click here",
            Modifier.clickable { navigateToRegisterScreen() },
            color = Color.White,
            style = MaterialTheme.typography.displayLarge
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