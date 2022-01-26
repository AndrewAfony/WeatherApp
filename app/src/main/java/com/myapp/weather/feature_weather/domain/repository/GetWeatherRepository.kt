package com.myapp.weather.feature_weather.domain.repository

import com.myapp.weather.core.util.Resource
import com.myapp.weather.feature_weather.domain.model.Weather
import com.myapp.weather.feature_weather.domain.model.city.CityItem
import kotlinx.coroutines.flow.Flow

interface GetWeatherRepository {

    fun getWeather(city: String): Flow<Resource<Weather>>

    fun searchCity(city: String): Flow<Resource<List<CityItem>>>
}