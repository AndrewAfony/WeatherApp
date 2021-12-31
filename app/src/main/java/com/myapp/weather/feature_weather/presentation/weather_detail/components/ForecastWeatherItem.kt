package com.myapp.weather.feature_weather.presentation.weather_detail.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
import com.myapp.weather.feature_weather.domain.model.hourly_forecast_weather.Params
import com.myapp.weather.feature_weather.util.getTime

@Composable
fun ForecastWeatherItem(
    weather: Params
) {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(end = 14.dp)
            .border(width = 1.dp, color = Color.LightGray.copy(alpha = 0.35f), shape = RoundedCornerShape(10.dp))
            .padding(vertical = 6.dp, horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        weather.dt_txt?.let {
            Text(
                text = getTime(it),
                fontSize = 13.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Medium
            )
        }
        Icon(
            Icons.Default.Web,
            contentDescription = ""
        )
        Text(
            text = "${weather.main?.temp}°",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}