package com.myapp.weather.feature_weather.di

import com.myapp.weather.BuildConfig
import com.myapp.weather.feature_weather.data.remote.WeatherApi
import com.myapp.weather.feature_weather.data.repository.GetWeatherRepositoryImpl
import com.myapp.weather.feature_weather.domain.repository.GetWeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherModule {

    @Provides
    @Singleton
    fun provideGetWeatherRepository(api: WeatherApi): GetWeatherRepository {
        return GetWeatherRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
            .create(WeatherApi::class.java)
    }
}