package com.example.job_app.feature_auth.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.job_app.R
import com.example.job_app.feature_auth.viewmodel.RegisterViewModel
import com.example.job_app.ui.theme.JobappTheme
// Frederik + Victor
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    navigateToLoginScreen: () -> Unit) {
    val registerViewModel: RegisterViewModel = viewModel()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val padding = (screenWidth - (screenWidth * 0.8f)) / 2
    if (registerViewModel.shouldShowDialog) {
        AuthAlertDialog(title = "Fejl", text = "Der skete en fejl ved oprettelse af bruger")
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
            .fillMaxSize(0.8f)
            .background(Color(0xFF1565C0))
            .padding(top = 25.dp),
        verticalArrangement = Arrangement.spacedBy(1.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Image(
            painter = painterResource(id = R.drawable.logo),
            modifier = Modifier.size(200.dp),
            contentDescription = "Logo"
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "AnsøgNemt",
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 32.sp), color = Color.White,
        )
        Spacer(modifier = Modifier.height(60.dp))
        OutlinedTextField(
            value = registerViewModel.email,
            onValueChange = { registerViewModel.onEmailChange(it) },
            label = { Text("Email...", color = Color.White) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                disabledTextColor = Color.White,
                cursorColor = Color.White,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                disabledBorderColor = Color.Transparent,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White))

        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            value = registerViewModel.password,
            onValueChange = { registerViewModel.onPasswordChange(it) },
            label = { Text("Adgangskode...", color = Color.White) },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                disabledTextColor = Color.White,
                cursorColor = Color.White,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                disabledBorderColor = Color.Transparent,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White)
          )

        Spacer(modifier = Modifier.height(32.dp))
        
        Button(onClick = { registerViewModel.createAccount(onRegisterSuccess) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier
                .padding(horizontal = padding)
                .height(48.dp)
                .width(275.dp)
        ) {
            Text("Opret konto", color = Color(0xFF1565C0),style = MaterialTheme.typography.titleLarge.copy(fontSize = 25.sp))
        }

        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Har du allerede en konto? Klik her",
            color = Color.White,
            modifier = Modifier.clickable { navigateToLoginScreen() }
        )}
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    JobappTheme {
        RegisterScreen(onRegisterSuccess = {}, navigateToLoginScreen = {})
    }
}
