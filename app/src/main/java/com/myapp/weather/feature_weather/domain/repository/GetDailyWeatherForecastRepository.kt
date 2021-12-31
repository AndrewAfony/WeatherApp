package com.myapp.weather.feature_weather.domain.repository

import com.myapp.weather.core.util.Resource
import com.myapp.weather.feature_weather.domain.model.daily_forecast_weather.DailyForecast
import kotlinx.coroutines.flow.Flow

interface GetDailyWeatherForecastRepository {

    fun getDailyWeatherForecast(city: String): Flow<Resource<DailyForecast>>
}