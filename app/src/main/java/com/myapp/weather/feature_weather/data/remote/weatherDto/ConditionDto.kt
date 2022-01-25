package com.myapp.weather.feature_weather.data.remote.weatherDto

import com.myapp.weather.feature_weather.domain.model.Condition

data class ConditionDto(
    val code: Int,
    val icon: String,
    val text: String
) {
    fun toCondition(): Condition {
        return Condition(icon, text)
    }
}