package com.example.job_app.feature_profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.job_app.R
import com.example.job_app.ui.theme.JobappTheme

@Composable
fun EditScreen(
    name: String,
    surname: String,
    onChangePasswordClicked: () -> Unit,
    onChangeEmailClicked: () -> Unit,
    onDeleteAccountClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color(0xFF3F51B5)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val navController = rememberNavController()
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Navigate",
            modifier = Modifier.clickable { navController.navigate("home") })
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Profile Picture",
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = name, color = Color.White)
        Text(text = surname, color = Color.White)
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = onChangePasswordClicked,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ændre password")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onChangeEmailClicked,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ændre email")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onDeleteAccountClicked,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Red)
        ) {
            Text("Slet min konto", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditProfileScreenPreview() {
    JobappTheme {
        EditScreen(
            name = "Fornavn",
            surname = "Efternavn",
            onChangePasswordClicked = {},
            onChangeEmailClicked = {},
            onDeleteAccountClicked = {},

        )
    }
}
