package com.myapp.weather.feature_weather.data.repository

import com.myapp.weather.core.util.Resource
import com.myapp.weather.feature_weather.data.remote.WeatherApi
import com.myapp.weather.feature_weather.domain.model.forecast_weather.ForecastWeather
import com.myapp.weather.feature_weather.domain.repository.GetWeatherForecastRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetWeatherForecastRepositoryImpl @Inject constructor(
    private val api: WeatherApi
): GetWeatherForecastRepository {

    override fun getWeatherForecast(city: String): Flow<Resource<ForecastWeather>> = flow {

        emit(Resource.Loading())

        try {
            val weatherForecast = api.getWeatherForecast(city).toForecastWeather()
            emit(Resource.Success<ForecastWeather>(data = weatherForecast))
        } catch (e: HttpException) {
            emit(Resource.Error<ForecastWeather>(message = e.localizedMessage ?: ""))
        } catch (e: IOException) {
            emit(Resource.Error<ForecastWeather>(message = e.localizedMessage ?: ""))
        }

    }
}