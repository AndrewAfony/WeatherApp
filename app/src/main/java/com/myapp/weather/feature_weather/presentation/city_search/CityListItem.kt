package com.myapp.weather.feature_weather.presentation.city_search

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myapp.weather.feature_weather.domain.model.city.CityItem

@Composable
fun CityListItem(
    city: CityItem,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.LocationOn, contentDescription = "")
            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "${city.name}, ",
                    fontSize = 15.sp
                )
                Text(
                    text = city.country,
                    fontSize = 15.sp,
                    color = Color.DarkGray
                )
            }
            Divider(modifier = Modifier.fillMaxHeight().width(1.dp))
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(
                    text = "lat: ${city.lat}"
                )
                Text(
                    text = "lon: ${city.lon}"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    CityListItem(city = CityItem(
        "Russia",
        11.2,
        23.2,
        "Moscow",
        "Moscow City"
    ))
}