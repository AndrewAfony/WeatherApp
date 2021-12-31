package com.myapp.weather.feature_weather.domain.repository

import com.myapp.weather.core.util.Resource
import com.myapp.weather.feature_weather.domain.model.daily_forecast_weather.DailyForecast
import com.myapp.weather.feature_weather.domain.model.hourly_forecast_weather.ForecastWeather
import kotlinx.coroutines.flow.Flow

interface GetHourlyWeatherForecastRepository {

    fun getHourlyWeatherForecast(city: String): Flow<Resource<ForecastWeather>>

}