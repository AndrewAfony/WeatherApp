package com.myapp.weather.feature_weather.data.repository

import com.myapp.weather.core.util.Resource
import com.myapp.weather.feature_weather.data.remote.WeatherApi
import com.myapp.weather.feature_weather.domain.model.current_weather.CurrentWeather
import com.myapp.weather.feature_weather.domain.repository.GetCurrentWeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCurrentWeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
): GetCurrentWeatherRepository {

    override fun getCurrentWeather(city: String): Flow<Resource<CurrentWeather>> = flow{

        emit(Resource.Loading<CurrentWeather>())

        try {
            val currentWeather = api.getCurrentWeather(city).toCurrentWeather()
            emit(Resource.Success<CurrentWeather>(data = currentWeather))
        } catch (e: HttpException) {
            emit(Resource.Error<CurrentWeather>(message = e.localizedMessage ?: ""))
        } catch (e: IOException) {
            emit(Resource.Error<CurrentWeather>(message = e.localizedMessage ?: ""))
        }
    }

}