package com.myapp.weather.feature_weather.presentation.weather_detail

import com.myapp.weather.feature_weather.domain.model.Hour

data class HourlyForecastState(
    val isLoading: Boolean = false,
    val weatherForecast: List<Hour>? = null
)
