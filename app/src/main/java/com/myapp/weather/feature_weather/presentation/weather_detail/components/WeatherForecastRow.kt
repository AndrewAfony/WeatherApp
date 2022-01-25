package com.myapp.weather.feature_weather.presentation.weather_detail.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.myapp.weather.feature_weather.domain.model.Hour
import com.myapp.weather.feature_weather.domain.model.Weather

@Composable
fun WeatherForecastRow(
    weather: List<Hour>
) {
    LazyRow(
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(weather) { index, item ->
            ForecastWeatherItem(item)
        }
    }
}