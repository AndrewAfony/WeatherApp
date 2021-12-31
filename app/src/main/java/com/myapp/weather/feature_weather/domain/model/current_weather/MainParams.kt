package com.myapp.weather.feature_weather.domain.model.current_weather

data class MainParams(
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
)
