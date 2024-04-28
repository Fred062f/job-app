package com.example.job_app.feature_home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.job_app.feature_home.models.JobApplication
import com.example.job_app.feature_home.viewmodel.HomeViewModel
import com.example.job_app.ui.theme.JobappTheme
import java.time.LocalDate

@Composable
fun DateHeader() {
    val current = LocalDate.now().toString()
    Text(
        text = "Dato i dag: $current",
        color = Color.Gray,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp
    )
}

@Composable
fun ApplicationDeadlines() {
    Text(
        text = "Kommende ansøgningsfrister:",
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
    Spacer(modifier = Modifier.height(5.dp))
}

@Composable
fun BottomFloatingActionButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        LargeFloatingActionButton(
            onClick = { onClick() },
            shape = CircleShape,
            containerColor = Color(46, 90, 186),
            contentColor = Color.White
        ) {
            Icon(Icons.Filled.Add, "Large floating action button")
        }
    }
}

@Composable
fun TopNavigationBar(
    navigateToLoginScreen: () -> Unit)
{
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
            contentDescription = "Profile",
            tint = Color.DarkGray,
            modifier = Modifier
                .size(55.dp)
                .padding(8.dp)
                .clickable(onClick = { navigateToLoginScreen() })
        )
    }
}

@Composable
fun AlternativeTopNavigationBar(
    navigateToLoginScreen: () -> Unit,
    navigateBack: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = Color.DarkGray,
            modifier = Modifier
                .size(55.dp)
                .padding(8.dp)
                .clickable(onClick = { navigateBack() })
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
            contentDescription = "Logout",
            tint = Color.DarkGray,
            modifier = Modifier
                .size(55.dp)
                .padding(8.dp)
                .clickable(onClick = { navigateToLoginScreen() })
        )
    }
}

@Composable
fun TestScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        TopNavigationBar {

        }
        DateHeader()
        ApplicationDeadlines()
        HorizontalDivider()
        ListItemHeaders()
        HorizontalDivider()
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .background(Color.LightGray)) {
            /* TODO */
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center) {
                Text(text = "Ingen kommende ansøgningsfrister", fontWeight = FontWeight.Bold)
            }
        }
        HorizontalDivider()
        BottomFloatingActionButton {}
    }
}

@Composable
fun ListItemHeaders() {
    Column {
        androidx.compose.material3.ListItem(
            headlineContent = {
                Text(
                    text = "Ansøgning",
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )
            },
            trailingContent = {
                Text(
                    text = "Fjern",
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )
            },
            leadingContent = {
                Text(
                    text = "Status",
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )
            }
        )
    }
}

@Composable
fun ListItem(items: List<JobApplication>) {
    val homeViewModel: HomeViewModel = viewModel()
    val userId = homeViewModel.getCurrentUser()?.uid
    LazyColumn {
        items(items) { item ->
            Column {
                androidx.compose.material3.ListItem(
                    headlineContent = { Text(text = item.jobTitle.toString()) },
                    supportingContent = { Text(text = item.timestamp?.toDate().toString()) },
                    trailingContent = {
                        IconButton(onClick = {
                            if (userId != null) {
                                homeViewModel.deleteData(userId, item.id.toString())
                                // Trigger Recomposition
                            }
                        }) {
                            Icon(
                                Icons.Filled.Delete,
                                contentDescription = "Localized description",
                            )
                        }
                    },
                    leadingContent = {
                        IconButton(onClick = {
                            if (userId != null) {
                                item.status?.let {
                                    homeViewModel.updateData(userId, item.id.toString(),
                                        it
                                    )
                                }
                            }
                        }) {
                            if (item.status == true) {
                                Icon(
                                    Icons.Filled.CheckCircle,
                                    tint = Color.Green,
                                    contentDescription = "Localized description",
                                )
                            } else {
                                Icon(
                                    Icons.Filled.CheckCircle,
                                    tint = Color.Red,
                                    contentDescription = "Localized description",
                                )
                            }
                        }
                    }
                )
            }
            HorizontalDivider()
        }
    }
}
@Preview(showBackground = true)
@Composable
fun TestScreenPreview() {
    JobappTheme {
        TestScreen()
    }
}