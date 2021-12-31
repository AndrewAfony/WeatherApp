package com.myapp.weather.feature_weather.presentation.weather_detail

import com.myapp.weather.feature_weather.domain.model.hourly_forecast_weather.ForecastWeather

data class WeatherForecastState(
    val isLoading: Boolean = false,
    val weatherForecast: ForecastWeather? = null
)
