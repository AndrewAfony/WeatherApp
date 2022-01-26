package com.myapp.weather.feature_weather.domain.use_cases

import com.myapp.weather.core.util.Resource
import com.myapp.weather.feature_weather.domain.model.city.CityItem
import com.myapp.weather.feature_weather.domain.repository.GetWeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(
    private val repository: GetWeatherRepository
) {
    operator fun invoke(city: String): Flow<Resource<List<CityItem>>> {

        if(city.isBlank()) return flow {}

        return repository.searchCity(city)
    }
}