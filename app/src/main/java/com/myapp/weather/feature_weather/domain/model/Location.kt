package com.myapp.weather.feature_weather.domain.model

data class Location(
    val country: String,
    val localtime: String,
    val name: String,
    val region: String,
    val tz_id: String
)
