package com.myapp.weather.feature_weather.domain.model.hourly_forecast_weather

data class ForecastWeather(
    val city: City?,
    val forecast: List<Params?>
)