package com.myapp.weather.feature_weather.presentation.weather_detail.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.myapp.weather.feature_weather.domain.model.Hour
import com.myapp.weather.feature_weather.domain.model.Weather
import com.myapp.weather.feature_weather.util.getCurrentTime
import com.myapp.weather.feature_weather.util.getTime

@Composable
fun WeatherForecastRow(
    weather: List<Hour>
) {
    LazyRow(
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(weather) { index, item ->
            if (index >= getCurrentTime().toInt() - 2) {
                if(getTime(item.time).substringBefore(":") == getCurrentTime()) {
                    FirstForecastWeatherItem(weather = item)
                } else {
                    ForecastWeatherItem(item)
                }
            }
        }
    }
}