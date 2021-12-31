package com.myapp.weather.feature_weather.domain.model.daily_forecast_weather

import com.myapp.weather.feature_weather.data.remote.dailyForecastDto.DataDto

data class DailyForecast(
    val city_name: String,
    val country_code: String,
    val `data`: List<DataDto>,
)
