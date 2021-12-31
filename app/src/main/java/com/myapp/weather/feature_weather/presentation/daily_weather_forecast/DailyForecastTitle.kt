package com.myapp.weather.feature_weather.presentation.daily_weather_forecast

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DailyForecastTitle(
    city: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ){
        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = null)
        }
        Text(
            text = city,
            modifier = Modifier.weight(5f),
            textAlign = TextAlign.Center
        )
        Box(modifier = Modifier.weight(1f)){}
    }
}

@Preview(showBackground = true)
@Composable
fun prev() {
    DailyForecastTitle(city = "Moscow")
}