package com.myapp.weather.model

data class MainWeather(
    val main: Main?,
    val weather: List<WeatherDescription>?
)