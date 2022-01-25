package com.myapp.weather.feature_weather.domain.model

data class Weather(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)
