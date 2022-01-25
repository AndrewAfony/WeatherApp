package com.myapp.weather.feature_weather.presentation.weather_detail

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.weather.core.util.Resource
import com.myapp.weather.feature_weather.domain.use_cases.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getCurrentWeather: GetWeatherUseCase
): ViewModel() {

    var searchQuery = mutableStateOf("Moscow")
    private set

    var currentWeatherState = mutableStateOf(CurrentWeatherState())
    private set

    var weatherForecastState = mutableStateOf(HourlyForecastState())
    private set

    var eventFlow = MutableSharedFlow<UIEvent>()
    private set

    private var searchJob: Job? = null

    init {
        onSearch(searchQuery.value)
    }

    fun onSearch(city: String) {
        searchQuery.value = city
        searchJob = viewModelScope.launch {
            getCurrentWeather(city)
                .onEach { result ->
                    when(result) {
                        is Resource.Success -> {
                            currentWeatherState.value = currentWeatherState.value.copy(
                                isLoading = false,
                                currentWeather = result.data
                            )
                            weatherForecastState.value = weatherForecastState.value.copy(
                                isLoading = false,
                                weatherForecast = result.data?.forecast?.forecastday?.get(0)?.hour
                            )
                            Log.d("Result", "First screen")
                        }
                        is Resource.Error -> {
                            currentWeatherState.value = currentWeatherState.value.copy(
                                isLoading = false,
                                currentWeather = result.data
                            )
                            weatherForecastState.value = weatherForecastState.value.copy(
                                isLoading = false,
                                weatherForecast = result.data?.forecast?.forecastday?.get(0)?.hour
                            )
                            eventFlow.emit(UIEvent.ShowSnackbar(
                                message = result.message ?: "Unknown error"
                            ))
                        }
                        is Resource.Loading -> {
                            currentWeatherState.value = currentWeatherState.value.copy(
                                isLoading = true,
                                currentWeather = result.data
                            )
                            weatherForecastState.value = weatherForecastState.value.copy(
                                isLoading = false,
                                weatherForecast = result.data?.forecast?.forecastday?.get(0)?.hour
                            )
                        }
                    }
                }.launchIn(this)
        }
    }

    sealed class UIEvent {
        data class ShowSnackbar(val message: String): UIEvent()
    }
}