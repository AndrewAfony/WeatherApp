package com.myapp.weather.feature_weather.domain.repository

import com.myapp.weather.core.util.Resource
import com.myapp.weather.feature_weather.domain.model.current_weather.CurrentWeather
import kotlinx.coroutines.flow.Flow

interface GetCurrentWeatherRepository {

    fun getCurrentWeather(city: String): Flow<Resource<CurrentWeather>>

}