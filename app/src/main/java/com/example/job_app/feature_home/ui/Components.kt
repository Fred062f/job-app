package com.example.job_app.feature_home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.job_app.feature_home.models.JobApplication
import com.example.job_app.feature_home.viewmodel.HomeViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

// Frederik
@Composable
fun DateHeader() {
    val current = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy") // Define your desired date format
    val formattedDate = current.format(formatter)
    Text(
        text = "Dato i dag: $formattedDate",
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
        fontSize = 18.sp
    )
    Spacer(modifier = Modifier.height(5.dp))
}

@Composable
fun BottomFloatingActionButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopEnd
        //contentAlignment = Alignment.BottomEnd
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
fun ListItem(
    items: List<JobApplication>,
    navController: NavController
) {
    val homeViewModel: HomeViewModel = viewModel()
    val userId = homeViewModel.getCurrentUser()?.uid
    val sorted = items.sortedBy { it.timestamp }

    if (homeViewModel.shouldShowDialog) {
        ConfirmDialogComponent(
            title = "Slet",
            text = "Er du sikker på at du vil slette ansøgningensfristen fra din liste?",
            onConfirm = {
                if (userId != null) {
                    homeViewModel.deleteData(userId, homeViewModel.itemId)
                }
            }
        )
    }

    LazyColumn {
        items(sorted) { item ->
            Column {
                androidx.compose.material3.ListItem(
                    headlineContent = {
                        Text(
                            text = item.jobTitle?.let { it } ?: "Unknown",
                            modifier = Modifier.clickable { item.id?.let { navController.navigate("application/$it") } }
                        )
                    },
                    supportingContent = { item.timestamp?.let { timestamp ->
                        // Convert timestamp to Date object
                        val date = timestamp.toDate()
                        // Define your desired date format
                        val dateFormat = SimpleDateFormat("dd-MM-yyyy")
                        // Format the Date object as a string
                        val dateString = dateFormat.format(date)
                        // Display the formatted date string
                        Text(text = dateString)
                    } ?: run {
                        // Handle case where timestamp is null
                        Text(text = "No timestamp available")
                    }},
                    trailingContent = {
                        IconButton(onClick = {
                            if (userId != null) {
                                homeViewModel.itemId = item.id.toString()
                                homeViewModel.shouldShowDialog = true
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
                                    tint = Color(0, 128, 0),
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