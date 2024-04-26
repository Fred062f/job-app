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
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.job_app.feature_home.models.JobApplication
import java.time.LocalDate

@Composable
fun ItemList(
    items: List<JobApplication>,
    navigateToApplicationInfoScreen: () -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color(46, 90, 186),
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        LazyColumn(modifier = Modifier
            .background(Color(46, 90, 186))) {
            items(items) { item ->
                Column(
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navigateToApplicationInfoScreen() },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        ApplicationTitle(item.jobTitle.toString())
                        item.status?.let { DisplayIconBasedOnStatus(boolean = it) }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Spacer(modifier = Modifier.width(30.dp))
                        Icon(Icons.Default.DateRange, null, tint = Color.Gray, modifier = Modifier.size(16.dp))
                        item.timestamp?.let { Text(it.toDate().toString()) }
                    }
                }
            }
        }
    }
}

@Composable
fun DisplayIconBasedOnStatus(boolean: Boolean) {
    if (boolean) {
        Icon(Icons.Default.CheckCircle, null, tint = Color.Green, modifier = Modifier.size(30.dp))
    }
    else {
        Icon(Icons.Default.CheckCircle, null, tint = Color.Red, modifier = Modifier.size(30.dp))
    }
}

@Composable
fun DateHeader() {
    val current = LocalDate.now().toString()
    Text(
        text = current,
        color = Color.Gray,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp
    )
}

@Composable
fun ApplicationDeadlines() {
    Text(
        text = "Kommende ansøgningsfrister",
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
fun ApplicationTitle(job: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        Text("o", color = Color.White)
        Spacer(modifier = Modifier.width(20.dp))
        Text(job, color = Color.White, fontSize = 20.sp)
    }
}

@Composable
fun TopNavigationBar(
    navigateToProfileScreen: () -> Unit)
{
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Icon(
            imageVector = Icons.Rounded.AccountCircle,
            contentDescription = "Profile",
            tint = Color.DarkGray,
            modifier = Modifier
                .size(65.dp)
                .padding(8.dp)
                .clickable(onClick = { navigateToProfileScreen() })
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewItemList() {
    val items = listOf(JobApplication())
    ItemList(items, navigateToApplicationInfoScreen = {})
}
