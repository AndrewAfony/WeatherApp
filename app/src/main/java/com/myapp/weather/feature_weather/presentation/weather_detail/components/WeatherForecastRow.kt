package com.myapp.weather.feature_weather.presentation.weather_detail.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.myapp.weather.feature_weather.domain.model.forecast_weather.ForecastWeather

@Composable
fun WeatherForecastRow(
    weather: ForecastWeather
) {
    LazyRow(
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(weather.forecast) { index, item ->

            if (index == 0) {
                if (item != null) {
                    FirstForecastWeatherItem(item)
                }
            } else {
                if (item != null) {
                    ForecastWeatherItem(item)
                }
            }
        }
    }
}