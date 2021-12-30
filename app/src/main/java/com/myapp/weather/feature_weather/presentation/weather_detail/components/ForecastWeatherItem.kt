package com.myapp.weather.feature_weather.presentation.weather_detail.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Web
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ForecastWeatherItem() {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(end = 14.dp)
            .border(width = 1.dp, color = Color.LightGray.copy(alpha = 0.35f), shape = RoundedCornerShape(6.dp))
            .padding(vertical = 6.dp, horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = "12:00",
            fontSize = 13.sp,
            color = Color.Gray
        )
        Icon(
            Icons.Default.Web,
            contentDescription = ""
        )
        Text(
            text = "22°",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}