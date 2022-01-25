package com.myapp.weather.feature_weather.presentation.weather_detail.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myapp.weather.NavigationRoutes

@Composable
fun ForecastTitle(
    navController: NavController,
    city: String
 ) {

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Today",
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp
        )
        Text(
            text = "Next 7 Days ›",
            color = MaterialTheme.colors.onBackground.copy(alpha = 0.6f),
            fontSize = 18.sp,
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .clickable { navController.navigate(NavigationRoutes.WeatherForecastScreen.route + "/${city}") }
                .padding(4.dp)
        )
    }
}