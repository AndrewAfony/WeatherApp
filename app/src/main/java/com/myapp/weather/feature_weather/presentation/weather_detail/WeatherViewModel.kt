package com.myapp.weather.feature_weather.presentation.weather_detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.weather.core.util.Resource
import com.myapp.weather.feature_weather.domain.use_cases.GetCurrentWeatherUseCase
import com.myapp.weather.feature_weather.domain.use_cases.GetHourlyWeatherForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getCurrentWeather: GetCurrentWeatherUseCase,
    private val getWeatherForecast: GetHourlyWeatherForecastUseCase
): ViewModel() {

    var searchQuery = mutableStateOf("")
    private set

    var currentWeatherState = mutableStateOf(CurrentWeatherState())
    private set

    var weatherForecastState = mutableStateOf(WeatherForecastState())
    private set

    var eventFlow = MutableSharedFlow<UIEvent>()
    private set

    private var searchJob: Job? = null

    init {
        onSearch("Moscow")
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
                        }
                        is Resource.Error -> {
                            currentWeatherState.value = currentWeatherState.value.copy(
                                isLoading = false,
                                currentWeather = result.data
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
                        }
                    }
                }.launchIn(this)
            getWeatherForecast(city)
                .onEach { result ->
                    when(result) {
                        is Resource.Success -> {
                            weatherForecastState.value = weatherForecastState.value.copy(
                                isLoading = false,
                                weatherForecast = result.data
                            )
                        }
                        is Resource.Error -> {
                            weatherForecastState.value = weatherForecastState.value.copy(
                                isLoading = false,
                                weatherForecast = result.data
                            )
                            eventFlow.emit(UIEvent.ShowSnackbar(
                                message = result.message ?: "Unknown error"
                            ))
                        }
                        is Resource.Loading -> {
                            weatherForecastState.value = weatherForecastState.value.copy(
                                isLoading = true,
                                weatherForecast = result.data
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