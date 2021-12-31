package com.myapp.weather.feature_weather.data.repository

import com.myapp.weather.core.util.Resource
import com.myapp.weather.feature_weather.data.remote.DailyForecastWeatherApi
import com.myapp.weather.feature_weather.domain.model.daily_forecast_weather.DailyForecast
import com.myapp.weather.feature_weather.domain.repository.GetDailyWeatherForecastRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDailyWeatherForecastRepositoryImpl @Inject constructor(
    private val api: DailyForecastWeatherApi
):GetDailyWeatherForecastRepository {

    override fun getDailyWeatherForecast(city: String): Flow<Resource<DailyForecast>> = flow{

        emit(Resource.Loading())

        try {
            val weatherForecast = api.getDailyWeatherForecast(city).toDailyForecast()
            emit(Resource.Success<DailyForecast>(data = weatherForecast))
        } catch (e: HttpException) {
            emit(Resource.Error<DailyForecast>(message = e.localizedMessage ?: ""))
        } catch (e: IOException) {
            emit(Resource.Error<DailyForecast>(message = e.localizedMessage ?: ""))
        }
    }
}