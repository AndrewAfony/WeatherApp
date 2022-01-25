package com.myapp.weather.feature_weather.presentation.daily_weather_forecast

import com.myapp.weather.feature_weather.domain.model.Weather

data class DailyForecastState(
    val isLoading: Boolean = false,
    val dailyForecast: Weather? = null
)