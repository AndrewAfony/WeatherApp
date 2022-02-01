package com.myapp.weather.feature_weather.presentation.weather_detail.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.myapp.weather.NavigationRoutes
import com.myapp.weather.feature_weather.domain.model.city.CityItem
import com.myapp.weather.feature_weather.presentation.city_search.CityListItem
import com.myapp.weather.feature_weather.presentation.city_search.CitySearchViewModel
import com.myapp.weather.feature_weather.presentation.weather_detail.WeatherViewModel

@Composable
fun BottomSheetContent(
    searchViewModel: CitySearchViewModel,
    viewModel: WeatherViewModel,
    cities: List<CityItem>?
) {

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.padding(4.dp)
    ) {
        TextField(
            placeholder = { Text(text = "Search...") },
            value = searchViewModel.searchQuery,
            onValueChange = searchViewModel::onSearch,
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
        cities?.let { list ->
            LazyColumn(
                modifier = Modifier.padding(8.dp)
            ) {
                items(list) { item ->
                    CityListItem(
                        city = item,
                        modifier = Modifier
                            .clickable {
//                                navController.navigate(NavigationRoutes.WeatherDetailScreen.route + "/${item.name}")
                                viewModel.onUpdate(item.name)

                            }
                    )
                    Divider()

                }
            }
        }
    }
}