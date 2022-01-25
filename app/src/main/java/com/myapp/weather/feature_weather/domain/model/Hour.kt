package com.myapp.weather.feature_weather.domain.model

data class Hour(
    val feelslike_c: Double,
    val temp_c: Double,
    val time: String,
    val condition: Condition
    )
