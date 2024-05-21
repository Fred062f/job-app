package com.example.job_app.navigation.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.job_app.ui.theme.JobappTheme

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar(modifier = Modifier.border(1.dp, Color.LightGray, RoundedCornerShape(50.dp))) {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Hjem") },
            label = { Text("Hjem") },
            selected = false,
            onClick = { navController.navigate("home") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Create, contentDescription = "Feedback") },
            label = { Text("Feedback") },
            selected = false,
            onClick = { navController.navigate("request") }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    JobappTheme {
        BottomNavigationBar(navController = rememberNavController())
    }
}