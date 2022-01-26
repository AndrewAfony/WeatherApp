package com.myapp.weather.feature_weather.presentation.weather_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
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
import coil.compose.rememberImagePainter
import com.myapp.weather.feature_weather.domain.model.Hour
import com.myapp.weather.feature_weather.util.getTime

@Composable
fun FirstForecastWeatherItem(
    weather: Hour
) {

    Card(
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 3.dp,
        modifier = Modifier
            .padding(end = 14.dp),
        shape = RoundedCornerShape(10.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 6.dp, horizontal = 14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = getTime(weather.time),
                fontSize = 13.sp,
                color = Color.White
            )
            Image(
                painter = rememberImagePainter(
                    data = "https:${weather.condition.icon}"
                ),
                contentDescription = "",
                modifier = Modifier
                    .size(30.dp)
            )
            Text(
                text = "${weather.temp_c}°",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
        }
    }
}