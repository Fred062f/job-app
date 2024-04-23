package com.example.job_app.feature_profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.job_app.ui.theme.JobappTheme
import com.example.job_app.R

@Composable
fun MyDocumentsScreen() {
    // Antag, at vi har to lister med filnavne for hver sektion
    val cvs = remember { mutableStateListOf("File.txt", "File2.txt") }
    val applications = remember { mutableStateListOf("File.txt", "File2.txt") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Header for CV-delen
        Text(text = "Mine CV", fontWeight = FontWeight.Bold)
        // Liste over CV'er
        cvs.forEachIndexed { index, cv ->
            DocumentItem(fileName = cv, onDelete = { cvs.removeAt(index) })
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Header for ansøgningsdelen
        Text(text = "Mine Ansøgninger", fontWeight = FontWeight.Bold)
        // Liste over ansøgninger
        applications.forEachIndexed { index, application ->
            DocumentItem(fileName = application, onDelete = { applications.removeAt(index) })
        }
    }
}

@Composable
fun DocumentItem(fileName: String, onDelete: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Filtitel
        Text(text = fileName)

        // Rediger knap
        Button(
            onClick = { /* TODO: Implementer redigeringslogik */ },
            modifier = Modifier.height(30.dp) // Tilpas efter ønsket størrelse
        ) {
            Text(text = "Vis min ansøgning", fontSize = MaterialTheme.typography.labelSmall.fontSize)
        }

        // Sletningsikon
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Slet",
            modifier = Modifier
                .size(24.dp)
                .clickable { onDelete() }
                .clip(RoundedCornerShape(50))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyDocumentsScreenPreview() {
    JobappTheme {
        MyDocumentsScreen()
    }
}
