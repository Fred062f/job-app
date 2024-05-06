package com.example.job_app.feature_application_form.ui

// import androidx.lifecycle.viewmodel.compose.viewModel
// import com.example.job_app.feature_application_form.viewmodel.ApplicationViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.job_app.feature_application_form.viewmodel.addApplication
import com.example.job_app.feature_application_form.viewmodel.applicationClass
import com.example.job_app.ui.theme.JobappTheme


@Composable
fun ApplicationFormScreen() {


    // Udkommenteret da den skabte problemer med preview - Ved på nuværende tidspunkt ikke om den skal bruges.
    // val applicationViewModel: ApplicationViewModel = viewModel()
    var jobindexLink by remember { mutableStateOf("") }
    var jobTitle by remember { mutableStateOf("") }
    var jobLocation by remember { mutableStateOf("") }
    var jobDescription by remember { mutableStateOf("") }
    var coverDate by remember { mutableStateOf("") }
    var coverTime by remember { mutableStateOf("") }


    // Meget af den følgende kode giver god mening at lave til @Composables, så der ikke er behov for repitation.
    // Det arbejder vi på :^)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            TextButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.List, contentDescription = null, modifier = Modifier.size(50.dp))
            }
            TextButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Filled.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier.size(50.dp)
                )
            }
        }
        Row {

            Text(text = "Step 1: Indsæt link eller tast manuelt", fontSize = 20.sp)
        }
        Row {
            TextField(
                value = jobindexLink,
                onValueChange = { jobindexLink = it },
                maxLines = 1,
                label = {
                    Text(
                        text = "Indsæt Jobindex link",
                        modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Center
                    )
                }
            )
        }
        Spacer(modifier = Modifier.size(24.dp))
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Jobtitel:      ")
            TextField(
                value = jobTitle,
                onValueChange = { jobTitle = it },
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.size(12.dp))
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Lokation:    ")
            TextField(
                value = jobLocation,
                onValueChange = { jobLocation = it },
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.size(32.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Beskrivelse (valgfrit):")
            TextField(
                value = jobDescription,
                onValueChange = { jobDescription = it },
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.size(32.dp))
        Row {

            Text(text = "Step 2: Indtast frist for ansøgelse", fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.size(24.dp))
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Dato:          ")
            TextField(
                value = coverDate,
                onValueChange = { coverDate = it },
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.size(12.dp))
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Tidspunkt: ")
            TextField(
                value = coverTime,
                onValueChange = { coverTime = it },
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.size(12.dp))
        Spacer(modifier = Modifier.size(24.dp))
        Button(onClick = {
            println("hej")

            addApplication(
                applicationClass(
                    jobLink = jobindexLink,
                    jobTitle = jobTitle,
                    jobLocation = jobLocation,
                    jobDescription = jobDescription,
                    coverDate = coverDate,
                    coverTime = coverTime
                )
            )


        }) {
            Text(text = "Opret Ansøgning")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppFormPreviewTwo() {
    JobappTheme {
        ApplicationFormScreen()
    }
}



