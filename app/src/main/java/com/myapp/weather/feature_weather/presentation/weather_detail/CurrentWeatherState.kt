package com.myapp.weather.feature_weather.presentation.weather_detail

import com.myapp.weather.feature_weather.domain.model.Weather

data class CurrentWeatherState(
    val isLoading: Boolean = false,
    val currentWeather: Weather? = null
)
