package com.myapp.weather.feature_weather.presentation.daily_weather_forecast

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.myapp.weather.feature_weather.domain.model.Location

@Composable
fun DailyForecastTitle(
    city: Location,
    navController: NavController
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ){
        IconButton(onClick = {navController.navigateUp() }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = null)
        }
        Row (
            modifier = Modifier.weight(5f),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${city.name}, ",
                textAlign = TextAlign.Center
            )
            Text(
                text = city.country,
                textAlign = TextAlign.Center,
                color = Color.LightGray,
            )
        }
        Box(modifier = Modifier.weight(1f)){}
    }
}