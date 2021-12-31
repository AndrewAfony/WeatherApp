package com.myapp.weather.feature_weather.domain.repository

import com.myapp.weather.core.util.Resource
import com.myapp.weather.feature_weather.domain.model.forecast_weather.ForecastWeather
import kotlinx.coroutines.flow.Flow

interface GetWeatherForecastRepository {

    fun getWeatherForecast(city: String): Flow<Resource<ForecastWeather>>
}