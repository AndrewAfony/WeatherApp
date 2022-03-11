package com.myapp.weather.feature_weather.presentation.daily_weather_forecast

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Update
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DailyForecastScreen(
    viewModel: DailyForecastViewModel = hiltViewModel(),
    navController: NavController
) {

    val state = viewModel.state
    val scaffoldState = rememberScaffoldState()

    val list = state.dailyForecast?.forecast?.forecastday

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is DailyForecastViewModel.UIEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Box(
            modifier = Modifier
                .padding(it)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                state.dailyForecast?.location?.let {
                    DailyForecastTitle(
                        city = it,
                        navController = navController
                    )
                }
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Next 3 Days",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    list?.let {
                        LazyColumn{
                            items(it) { item ->
                                DailyForecastListItem(weather = item)
                            }
                        }
                    }
                }
            }

        }
    }
}