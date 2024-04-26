package com.example.job_app.feature_auth.ui
import androidx.compose.foundation.Image
import androidx.compose.material3.OutlinedTextField
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
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
            .padding(16.dp)
            .background(Color(0xFF1565C0)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


// Logo

        Card(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally),
            shape = CircleShape
        ) {
            // Place your logo image here
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                modifier = Modifier.size(190.dp),
                contentDescription = "Logo"
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

// Wrapper
        Box(
            modifier = Modifier
                .fillMaxWidth(0.85f)
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
                unfocusedTextColor = Color.Black,
                disabledTextColor = Color.Gray,
                cursorColor = Color.White,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                disabledBorderColor = Color.Transparent,
                focusedLabelColor = Color.Gray,
                unfocusedLabelColor = Color.Gray
            )
        )
        }
        Spacer(modifier = Modifier.height(6.dp))


        OutlinedTextField(
            value = loginViewModel.password,
            onValueChange = { loginViewModel.onPasswordChange(it) },
            label = {Text("Password", color = Color.White) },
            singleLine = true,
            modifier = Modifier
            .fillMaxWidth(0.85f),
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.Black,
                disabledTextColor = Color.Gray,
                cursorColor = Color.White,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                disabledBorderColor = Color.Transparent,
                focusedLabelColor = Color.Gray,
                unfocusedLabelColor = Color.Gray
            ),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { loginViewModel.signIn(onLoginSuccess) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier
                .padding(horizontal = padding)
                .height(48.dp)
                .width(300.dp)
        ) {
            Text("Login", color = Color(0xFF1565C0))
        }
            Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {  navigateToRegisterScreen()  },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier
                .padding(horizontal = padding * 2)
                .height(48.dp)
                .width(300.dp)

        ) {
            Text("Register", color = Color(0xFF1565C0))
        }
    }


}
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    JobappTheme {
        LoginScreen(onLoginSuccess = {}, navigateToRegisterScreen = {})
    }
}