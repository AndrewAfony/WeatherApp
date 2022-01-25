package com.myapp.weather.feature_weather.domain.use_cases

import com.myapp.weather.core.util.Resource
import com.myapp.weather.feature_weather.domain.model.Current
import com.myapp.weather.feature_weather.domain.model.Weather
import com.myapp.weather.feature_weather.domain.repository.GetWeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val repository: GetWeatherRepository
) {

    operator fun invoke(city: String): Flow<Resource<Weather>> {

        if(city.isBlank()) return flow {}

        return repository.getWeather(city)

    }
}