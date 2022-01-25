package com.myapp.weather.feature_weather.presentation.weather_detail.components

import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.myapp.weather.feature_weather.domain.model.Weather
import com.myapp.weather.feature_weather.util.getCurrentDate

@Composable
fun WeatherCard(
    weather: Weather
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
            Image (
                painter = rememberImagePainter(
                    data = "https:${weather.current.condition.icon}"
                ),
                contentDescription = "Weather icon",
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 16.dp)
                    .size(50.dp)
            )
            Text(
                text = weather.current.condition.text,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = getCurrentDate(),
                color = Color(0x83FFFFFF)
            )
            Text(
                text = "${weather.current.temp_c}°",
                fontSize = 64.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 16.dp)
            )
        }
    }
}