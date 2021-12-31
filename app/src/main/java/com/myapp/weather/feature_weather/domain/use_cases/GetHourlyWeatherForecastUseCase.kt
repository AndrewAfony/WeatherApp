package com.myapp.weather.feature_weather.domain.use_cases

import com.myapp.weather.core.util.Resource
import com.myapp.weather.feature_weather.domain.model.hourly_forecast_weather.ForecastWeather
import com.myapp.weather.feature_weather.domain.repository.GetHourlyWeatherForecastRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHourlyWeatherForecastUseCase @Inject constructor(
    private val repository: GetHourlyWeatherForecastRepository
) {

    operator fun invoke(city: String): Flow<Resource<ForecastWeather>> {

        return repository.getHourlyWeatherForecast(city)
    }
}