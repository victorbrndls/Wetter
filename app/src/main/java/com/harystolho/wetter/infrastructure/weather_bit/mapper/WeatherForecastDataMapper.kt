package com.harystolho.wetter.infrastructure.weather_bit.mapper

import android.util.Log
import com.harystolho.wetter.core.domain.WeatherForecast
import com.harystolho.wetter.infrastructure.weather_bit.data.WeatherForecastData
import com.harystolho.wetter.util.Mapper
import java.lang.Exception
import java.util.*

class WeatherForecastDataMapper : Mapper<WeatherForecastData, List<WeatherForecast>> {

    override fun map(obj: WeatherForecastData): List<WeatherForecast> {
        require(obj.data.isNotEmpty()) { "data field can't be empty" }

        return obj.data.mapNotNull { forecast ->
            try {
                WeatherForecast(
                    obj.city_name,
                    isoStringToCalendar(forecast.valid_date),
                    forecast.max_temp,
                    forecast.min_temp,
                    forecast.app_max_temp,
                    forecast.app_min_temp,
                    forecast.wind_spd,
                    forecast.wind_cdir_full,
                    obj.timezone,
                    forecast.pop,
                    forecast.rh,
                    forecast.clouds
                )
            } catch (e: Exception) {
                Log.e(TAG, "Error mapping weather forecast data", e)
                null
            }
        }
    }

    /**
     * @param iso YYYY-MM-DD
     *
     * @throws [IllegalArgumentException] if [iso] is not a valid ISO date
     * @throws [NumberFormatException] if a value can't be converted to a number
     */
    private fun isoStringToCalendar(iso: String): Calendar {
        return Calendar.getInstance().apply {
            require(iso.count { it == '-' } == 2) { "Invalid ISO date | date: $iso" }

            val fields = iso.split("-")

            set(Calendar.YEAR, fields[0].toInt())
            set(Calendar.MONTH, fields[1].toInt() - 1) // January is 0
            set(Calendar.DAY_OF_MONTH, fields[2].toInt())

            set(Calendar.HOUR, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }
    }

    companion object {
        private const val TAG = "WeatherForecastMapper"
    }

}