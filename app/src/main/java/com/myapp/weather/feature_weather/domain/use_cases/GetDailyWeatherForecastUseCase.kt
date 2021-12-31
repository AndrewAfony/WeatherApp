package com.myapp.weather.feature_weather.domain.use_cases

import com.myapp.weather.core.util.Resource
import com.myapp.weather.feature_weather.domain.model.daily_forecast_weather.DailyForecast
import com.myapp.weather.feature_weather.domain.repository.GetDailyWeatherForecastRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDailyWeatherForecastUseCase @Inject constructor(
    private val repository: GetDailyWeatherForecastRepository
) {
    operator fun invoke(city: String): Flow<Resource<DailyForecast>> {
        return repository.getDailyWeatherForecast(city)
    }
}