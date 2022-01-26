package com.myapp.weather.feature_weather.domain.model.city

data class CityItem(
    val country: String,
    val lat: Double,
    val lon: Double,
    val name: String,
    val region: String,
)
