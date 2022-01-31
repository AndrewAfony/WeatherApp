package com.myapp.weather.feature_weather.domain.model

data class Current(
    val condition: Condition,
    val feelslike_c: Double,
    val humidity: Int,
    val last_updated: String,
    val precip_mm: Double,
    val pressure_mb: Double,
    val temp_c: Double,
    val uv: Double,
    val wind_kph: Double
)
