package com.myapp.weather.feature_weather.presentation.city_search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.weather.core.util.Resource
import com.myapp.weather.feature_weather.domain.use_cases.GetCitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitySearchViewModel @Inject constructor(
    private val getCity: GetCitiesUseCase
): ViewModel() {

    var searchQuery by mutableStateOf("")
        private set

    var cities by mutableStateOf(CitiesState())

    var eventFlow = MutableSharedFlow<UIEvent>()
        private set

    var searchJob: Job? = null

    fun onSearch(city: String) {
        searchQuery = city
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            getCity(city)
                .onEach { result ->
                    when(result) {
                        is Resource.Success -> {
                            cities = cities.copy(
                                isLoading = false,
                                cities = result.data
                            )
                        }
                        is Resource.Error -> {
                            cities = cities.copy(
                                isLoading = false,
                                cities = result.data
                            )
                            eventFlow.emit(
                                UIEvent.ShowSnackbar(
                                message = result.message ?: "Unknown error"
                            ))
                        }
                        is Resource.Loading -> {
                            cities = cities.copy(
                                isLoading = true,
                                cities = result.data
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