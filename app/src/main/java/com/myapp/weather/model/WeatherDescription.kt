package com.myapp.weather.model

data class WeatherDescription(
    val description: String = "Sunny",
    val icon: String = "10d",
    val id: Int = 0,
    val main: String = ""
)