package com.myapp.weather.feature_weather.domain.model

data class Current(
    val condition: Condition,
    val feelslike_c: Double,
    val humidity: Int,
    val last_updated: String,
    val precip_mm: Int,
    val pressure_mb: Int,
    val temp_c: Int,
    val uv: Int,
    val wind_kph: Double
)
