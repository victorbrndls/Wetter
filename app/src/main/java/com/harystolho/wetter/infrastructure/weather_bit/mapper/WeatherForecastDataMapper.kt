package com.harystolho.wetter.infrastructure.weather_bit.mapper

import com.harystolho.wetter.core.domain.WeatherForecast
import com.harystolho.wetter.infrastructure.weather_bit.data.WeatherForecastData
import com.harystolho.wetter.util.DateUtils
import com.harystolho.wetter.util.Mapper

class WeatherForecastDataMapper : Mapper<WeatherForecastData, List<WeatherForecast>> {

    override fun map(obj: WeatherForecastData): List<WeatherForecast> {
        require(obj.data.isNotEmpty()) { "data field can't be empty" }

        return obj.data.map { forecast ->
            WeatherForecast(
                obj.city_name,
                DateUtils.isoStringToCalendar(forecast.valid_date),
                forecast.max_temp,
                forecast.min_temp,
                forecast.app_max_temp,
                forecast.app_min_temp,
                forecast.wind_spd,
                forecast.wind_cdir_full,
                obj.timezone,
                forecast.pop,
                forecast.precip,
                forecast.rh,
                forecast.clouds
            )
        }
    }
}
