package com.myapp.weather.feature_weather.presentation.weather_detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.weather.core.util.Resource
import com.myapp.weather.feature_weather.domain.model.CurrentWeather
import com.myapp.weather.feature_weather.domain.use_cases.GetCurrentWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val getCurrentWeather: GetCurrentWeatherUseCase
): ViewModel() {

    var searchQuery = mutableStateOf("")
    private set

    var state = mutableStateOf(CurrentWeatherState())
    private set

    var eventFlow = MutableSharedFlow<UIEvent>()
    private set

    private var searchJob: Job? = null

    init {
        onSearch("Moscow")
    }

    fun onSearch(city: String) {
        searchQuery.value = city
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            getCurrentWeather(city)
                .onEach { result ->
                    when(result) {
                        is Resource.Success -> {
                            state.value = state.value.copy(
                                isLoading = false,
                                currentWeather = result.data
                            )
                        }
                        is Resource.Error -> {
                            state.value = state.value.copy(
                                isLoading = false,
                                currentWeather = result.data
                            )
                            eventFlow.emit(UIEvent.ShowSnackbar(
                                message = result.message ?: "Unknown error"
                            ))
                        }
                        is Resource.Loading -> {
                            state.value = state.value.copy(
                                isLoading = true,
                                currentWeather = result.data
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