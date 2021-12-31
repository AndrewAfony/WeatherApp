package com.myapp.weather.feature_weather.domain.model.forecast_weather

data class ForecastWeather(
    val city: City?,
    val forecast: List<Params?>
)