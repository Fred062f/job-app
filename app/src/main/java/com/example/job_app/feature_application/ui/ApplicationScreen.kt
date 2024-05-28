package com.example.job_app.feature_application.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.job_app.feature_application.viewmodel.ApplicationViewModel
import com.example.job_app.feature_home.viewmodel.HomeViewModel
import com.example.job_app.navigation.ui.AlternativeTopNavigationBar
import com.example.job_app.navigation.ui.BottomNavigationBar
import com.example.job_app.ui.theme.JobappTheme
import java.text.SimpleDateFormat

// Frederik
@Composable
fun ApplicationScreen(
    id: String,
    userIsNotAuthorized: () -> Unit,
    navigateToLoginScreen: () -> Unit,
    navigateBack: () -> Unit,
    navController: NavController
) {
    val applicationViewModel: ApplicationViewModel = viewModel()
    val homeViewModel: HomeViewModel = viewModel()
    // If user is not authorized navigate to login screen
    if (!homeViewModel.userIsAuthorized()) return userIsNotAuthorized()

    applicationViewModel.initialize(id)

    val application = applicationViewModel.jobApplication.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AlternativeTopNavigationBar(navigateToLoginScreen, navigateBack)

        Column(modifier = Modifier.fillMaxSize(), Arrangement.spacedBy(30.dp)) {
            Spacer(modifier = Modifier.size(32.dp))

            if (application != null) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text("Job titel: ", fontWeight = FontWeight.Bold)
                    application.jobTitle?.let { Text(it) }
                }
                Spacer(modifier = Modifier.size(16.dp))

                Text("Beskrivelse:", fontWeight = FontWeight.Bold)
                application.description?.let { text ->
                    if (text.isEmpty()) {
                        Text("Ingen beskrivelse givet.")
                    } else {
                        Text(text)
                    }
                }
                Spacer(modifier = Modifier.size(16.dp))

                Row(modifier = Modifier.fillMaxWidth()){
                    Text("Status: ", fontWeight = FontWeight.Bold)
                    application.status?.let { boolean ->
                        if (boolean) {
                            Text("Jobansøgning er skrevet")
                        } else {
                            Text("Jobansøgning ikke skrevet")
                        }
                    }
                }
                Spacer(modifier = Modifier.size(16.dp))

                Row(modifier = Modifier.fillMaxWidth()){
                    Text("Ansøgningsfrist: ", fontWeight = FontWeight.Bold)
                    application.timestamp?.let { timestamp ->
                        val date = timestamp.toDate()
                        val dateFormat = SimpleDateFormat("dd-MM-yyyy")
                        val dateString = dateFormat.format(date)
                        Text(dateString)
                    }
                }
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomEnd
    ) {
        BottomNavigationBar(navController)
    }
}
@Preview(showBackground = true)
@Composable
fun ApplicationScreenPreview() {
    JobappTheme {
        ApplicationScreen(id = "", userIsNotAuthorized = {}, navigateToLoginScreen = {}, navigateBack = {}, navController = rememberNavController())
    }
}