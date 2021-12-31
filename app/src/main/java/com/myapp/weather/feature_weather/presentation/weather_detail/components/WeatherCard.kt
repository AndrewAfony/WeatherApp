package com.myapp.weather.feature_weather.presentation.weather_detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.myapp.weather.feature_weather.domain.model.current_weather.CurrentWeather
import com.myapp.weather.feature_weather.util.test

@Composable
fun WeatherCard(
    weather: CurrentWeather
) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // TODO("Загружать иконки погоды из API")
            Icon (
                painter = rememberImagePainter(
                    data = "https://cdn.onlinewebfonts.com/svg/img_553086.png"
                ),
                contentDescription = "Weather icon",
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 16.dp)
                    .size(50.dp)
            )
            Text(
                text = weather.weather[0].description,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = test(),
                color = Color(0x83FFFFFF)
            )
            Text(
                text = "${weather.main.temp}°",
                fontSize = 64.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 16.dp)
            )
            // TODO("Добавить макс и мин температуру")
        }
    }
}