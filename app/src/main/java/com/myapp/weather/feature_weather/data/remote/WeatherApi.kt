package com.myapp.weather.feature_weather.data.remote

import com.myapp.weather.BuildConfig
import com.myapp.weather.feature_weather.data.remote.weatherDto.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("v1/forecast.json")
    suspend fun getWeather(
        @Query("key") apiKey: String = BuildConfig.TOKEN,
        @Query("q") city: String = "Moscow",
        @Query("days") days: Int = 7
    ): WeatherDto
}