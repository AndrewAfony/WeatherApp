package com.myapp.weather.feature_weather.domain.model

data class CurrentWeather(
    val main: MainParams,
    val name: String,
    val timezone: Int,
    val weather: List<WeatherDescription>,
    val wind: Wind
)
