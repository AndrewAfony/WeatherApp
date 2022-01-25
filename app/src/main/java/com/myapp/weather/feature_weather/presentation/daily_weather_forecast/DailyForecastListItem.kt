package com.myapp.weather.feature_weather.presentation.daily_weather_forecast

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.myapp.weather.feature_weather.domain.model.ForecastDay
import com.myapp.weather.feature_weather.util.toDailyDate

@Composable
fun DailyForecastListItem(
    weather: ForecastDay
) {
    Row(
        modifier = Modifier
            .padding(14.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image (
            painter = rememberImagePainter(
                data = "https:${weather.day.condition.icon}"
            ),
            contentDescription = "Weather icon",
            modifier = Modifier
                .padding(top = 4.dp, bottom = 4.dp, end = 16.dp)
                .size(40.dp)
        )
        Text(
            text = toDailyDate(weather.date),
            modifier = Modifier
                .weight(1f)
        )
        Row(
            verticalAlignment = Alignment.Bottom
        ){
            Text(
                text = "${weather.day.maxtemp_c}°",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = " / ${weather.day.mintemp_c}°",
                fontSize = 15.sp,
                color = Color.LightGray,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun Preview() {
//    DailyForecastListItem(weather = ForecastDay())
//}