package com.myapp.weather.feature_weather.presentation.city_search

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.myapp.weather.NavigationRoutes
import kotlinx.coroutines.flow.collectLatest


@Composable
fun CitiesSearchScreen(
    viewModel: CitySearchViewModel = hiltViewModel(),
    navController: NavController
) {

    val cities = viewModel.cities

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is CitySearchViewModel.UIEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    val focusManager = LocalFocusManager.current

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
            ) {
                TextField(
                    value = viewModel.searchQuery,
                    onValueChange = viewModel::onSearch,
                    placeholder = { Text(text = "Search...") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onAny = { focusManager.clearFocus() }
                    )
                )
                cities.cities?.let { list ->
                    LazyColumn(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        items(list) { item ->

                            CityListItem(
                                city = item,
                                modifier = Modifier
                                    .clickable {
                                        navController.navigate(NavigationRoutes.WeatherDetailScreen.route + "/${item.name}")
                                    }
                            )
                            Divider()

                        }
                    }
                }
            }

            if(cities.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}