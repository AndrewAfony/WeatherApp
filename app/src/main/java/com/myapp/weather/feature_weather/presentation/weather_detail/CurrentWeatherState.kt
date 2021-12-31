package com.myapp.weather.feature_weather.presentation.weather_detail

import com.myapp.weather.feature_weather.domain.model.current_weather.CurrentWeather
import com.myapp.weather.feature_weather.domain.model.forecast_weather.ForecastWeather

data class CurrentWeatherState(
    val isLoading: Boolean = false,
    val currentWeather: CurrentWeather? = null
)