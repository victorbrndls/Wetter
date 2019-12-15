package com.harystolho.wetter.infrastructure.weather_bit.data

data class WeatherForecastData(
    var city_name: String,
    var timezone: String,
    var data: List<WeatherForecastDetails>
)

data class WeatherForecastDetails(
    var valid_date: String,
    var wind_spd: Double,
    var wind_cdir_full: String,
    var temp: Double,
    var max_temp: Double,
    var min_temp: Double,
    var app_max_temp: Double,
    var app_min_temp: Double,
    var pop: Double,
    var precip: Double,
    var rh: Double,
    var clouds: Double
)