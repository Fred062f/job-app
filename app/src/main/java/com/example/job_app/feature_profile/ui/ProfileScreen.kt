import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.job_app.R
import com.example.job_app.feature_home.viewmodel.ProfileViewModel
import com.example.job_app.ui.theme.JobappTheme


@Composable
fun ProfileScreen(navController: NavHostController) {
    val profileViewModel: ProfileViewModel = viewModel()
    val name = "profileViewModel.name"
    val email = "profileViewModel.email"
    val lastName = "profileViewModel.lastName"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Logged in as $email", fontWeight = Bold)

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Profile Picture",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "$name $lastName")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(onClick = { navController.navigate("editprofile") }) {
                Text(text = "Edit Details")
            }
            OutlinedButton(onClick = { navController.navigate("mydocuments") }) {
                Text(text = "My Documents")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            profileViewModel.signOut {
                navController.popBackStack(route = "login", inclusive = false)
            }
        }) {
            Text("Logout")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    JobappTheme {
        ProfileScreen(rememberNavController())
    }
}
