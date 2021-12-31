package com.myapp.weather.feature_weather.presentation.daily_weather_forecast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DailyForecastListItem() {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(Icons.Default.QuestionAnswer, contentDescription = "")
        Text(
            text = "Monday, 3 Oct"
        )
        Row(){
            Text(
                text = "max temp / "
            )
            Text(
                text = "min temp"
            )
        }
    }
}