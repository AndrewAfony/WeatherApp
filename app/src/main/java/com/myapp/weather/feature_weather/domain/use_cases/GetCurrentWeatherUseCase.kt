package com.myapp.weather.feature_weather.domain.use_cases

import com.myapp.weather.core.util.Resource
import com.myapp.weather.feature_weather.domain.model.CurrentWeather
import com.myapp.weather.feature_weather.domain.repository.GetCurrentWeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val repository: GetCurrentWeatherRepository
) {

    operator fun invoke(city: String): Flow<Resource<CurrentWeather>> {

        if(city.isBlank()) return flow {}

        return repository.getCurrentWeather(city)

    }
}