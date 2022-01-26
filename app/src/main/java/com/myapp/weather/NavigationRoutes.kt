package com.myapp.weather

sealed class NavigationRoutes(val route: String) {
    object WeatherDetailScreen: NavigationRoutes("weather_detail_screen")
    object WeatherForecastScreen: NavigationRoutes("weather_forecast_screen")
    object CitySearchScreen: NavigationRoutes("city_search_screen")
}
