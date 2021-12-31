package com.myapp.weather.feature_weather.presentation.daily_weather_forecast

import com.myapp.weather.feature_weather.domain.model.daily_forecast_weather.DailyForecast

data class DailyForecastState(
    val isLoading: Boolean = false,
    val dailyForecast: DailyForecast? = null
)