package com.harystolho.wetter.core.domain

import java.util.*

data class WeatherForecast(
    val cityName: String,

    val forecastDate: Calendar,

    val maxTemp: Double,
    val minTemp: Double,

    // "Feels like" max temperature
    val appMaxTemp: Double,
    // "Feels like" min temperature
    val appMinTem: Double,

    // meters / second
    val windSpeed: Double,
    val windDirection: String,

    // IANA timezone format
    val timezone: String,

    // a value between 0 and 1
    val precipitation: Double,

    // a value between 0 and 1
    val relativeHumidity: Double,

    // a value between 0 and 1
    val cloudsPercentage: Double
)