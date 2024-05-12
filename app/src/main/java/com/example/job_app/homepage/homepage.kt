package com.example.job_app.homepage

import android.widget.Button
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.job_app.R

val buttonNames = listOf("Ansøgning ->", "CV ->", "Jobsamtalen ->", "Kalender", "Andet")
val buttonNavigation = listOf("")

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun Homescreen() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                Icons.Filled.Settings,
                contentDescription = stringResource(
                    id = R.string.app_name

                ),
                modifier = Modifier.size(34.dp),
            )
            Icon(
                Icons.Filled.Info,
                contentDescription = stringResource(
                    id = R.string.app_name
                ),
                modifier = Modifier.size(34.dp),
            )
            Icon(
                Icons.Filled.AccountCircle,
                contentDescription = stringResource(
                    id = R.string.app_name
                ),
                modifier = Modifier.size(34.dp),
            )

        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            items(5) { index ->

                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .height(150.dp)
                ) {
                    Text(
                        text = buttonNames[index],
                        fontSize = 25.sp,
                        modifier = Modifier.padding(0.dp, 100.dp, 0.dp, 0.dp)
                    )

                }
            }
        }
    }
}


