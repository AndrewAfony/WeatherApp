package com.myapp.weather.feature_weather.domain.use_cases

import com.myapp.weather.core.util.Resource
import com.myapp.weather.feature_weather.domain.model.forecast_weather.ForecastWeather
import com.myapp.weather.feature_weather.domain.repository.GetWeatherForecastRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWeatherForecastUseCase @Inject constructor(
    private val repository: GetWeatherForecastRepository
) {

    operator fun invoke(city: String): Flow<Resource<ForecastWeather>> {

        return repository.getWeatherForecast(city)
    }
}