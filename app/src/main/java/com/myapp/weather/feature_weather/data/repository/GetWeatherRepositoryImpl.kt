package com.myapp.weather.feature_weather.data.repository

import android.util.Log
import com.myapp.weather.core.util.Resource
import com.myapp.weather.feature_weather.data.remote.WeatherApi
import com.myapp.weather.feature_weather.domain.model.Current
import com.myapp.weather.feature_weather.domain.model.Forecast
import com.myapp.weather.feature_weather.domain.model.Hour
import com.myapp.weather.feature_weather.domain.model.Weather
import com.myapp.weather.feature_weather.domain.repository.GetWeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetWeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
): GetWeatherRepository {

    override fun getWeather(city: String): Flow<Resource<Weather>> = flow{

        emit(Resource.Loading<Weather>())

        try {
            val currentWeather = api.getWeather(city = city).toWeather()
            emit(Resource.Success<Weather>(data = currentWeather))
        } catch (e: HttpException) {
            emit(Resource.Error<Weather>(message = e.localizedMessage ?: "Unknown error!"))
            Log.e("Error", e.localizedMessage ?: "No errors")
        } catch (e: IOException) {
            emit(Resource.Error<Weather>(message = e.localizedMessage ?: "Unknown error!"))
            Log.e("Error", e.localizedMessage ?: "No errors")
        }
    }
}