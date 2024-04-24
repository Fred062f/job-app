package com.example.job_app.feature_profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.job_app.ui.theme.JobappTheme
import com.example.job_app.R
import com.example.job_app.feature_home.ui.HomeScreen
import com.example.job_app.feature_home.viewmodel.HomeViewModel
import com.example.job_app.feature_home.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    navController: NavController
) {
    val profileViewModel: ProfileViewModel = viewModel()
    val name = profileViewModel.getCurrentUser()
    val email = profileViewModel.getCurrentUser()
    val lastName = profileViewModel.getCurrentUser()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Profile Picture"
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Profile Picture"
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "$name")
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(
                onClick = { navController.navigate("editprofile") },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Rediger oplysninger")
            }
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedButton(
                onClick = { navController.navigate("mydocuments") },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Mine dokumenter")
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "$name $lastName")
        Text(text = "$email")
    }
}
