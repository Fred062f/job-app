package com.example.job_app.feature_auth.ui
import androidx.compose.foundation.Image
import androidx.compose.material3.OutlinedTextField
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.job_app.R
import com.example.job_app.feature_auth.viewmodel.LoginViewModel
import com.example.job_app.ui.theme.JobappTheme



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    navigateToRegisterScreen: () -> Unit
) {
    val loginViewModel: LoginViewModel = viewModel()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val padding = (screenWidth - (screenWidth * 0.8f)) / 2
    if (loginViewModel.shouldShowDialog) {
        AlertDialog(text = "Login failure")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1565C0)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


            // Place your logo image here
            Image(
                painter = painterResource(id = R.drawable.logo),
                modifier = Modifier.size(200.dp),
                contentDescription = "Logo"
            )
            Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .background(Color.Transparent, RoundedCornerShape(24.dp)) // Set the background here
        ) {
        OutlinedTextField(
            value = loginViewModel.username,
            onValueChange = { loginViewModel.onUsernameChange(it) },
            label = { Text("Email...", color = Color.White) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.LightGray,
                disabledTextColor = Color.Gray,
                cursorColor = Color.White,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                disabledBorderColor = Color.Transparent,
                focusedLabelColor = Color.Gray,
                unfocusedLabelColor = Color.Gray))
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = loginViewModel.password,
            onValueChange = { loginViewModel.onPasswordChange(it) },
            label = {Text("Password...", color = Color.White) },
            singleLine = true,
            modifier = Modifier
            .fillMaxWidth(0.8f),
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.LightGray,
                disabledTextColor = Color.White,
                cursorColor = Color.White,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                disabledBorderColor = Color.Transparent,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White
            ),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { loginViewModel.signIn(onLoginSuccess) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier
                .padding(horizontal = padding)
                .height(48.dp)
                .width(275.dp)
        ) {
            Text("Login", color = Color(0xFF1565C0),style = MaterialTheme.typography.titleLarge.copy(fontSize = 25.sp))
        Button(onClick = { loginViewModel.signIn(onLoginSuccess) }) {
            Text("Log ind")
        }
            Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {  navigateToRegisterScreen()  },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier
                .padding(horizontal = padding * 2)
                .height(48.dp)
                .width(275.dp)

        ) {
            Text("Register", color = Color(0xFF1565C0),style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp ))
        }
    }
        Text(
            text = "Har du ikke en konto? Klik her",
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