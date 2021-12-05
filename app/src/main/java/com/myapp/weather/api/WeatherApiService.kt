package com.myapp.weather.api

import com.myapp.weather.BuildConfig
import com.myapp.weather.model.MainWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApiService {

    @GET("/data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("q") location: String,
        @Query("appid") api_key: String = BuildConfig.TOKEN,
        @Query("units") units: String = "metric"
    ) : Response<MainWeather>
}