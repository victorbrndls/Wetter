package com.harystolho.wetter.presentation.weather.forecast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.harystolho.wetter.core.domain.WeatherForecast
import com.harystolho.wetter.core.repository.WeatherForecastRepository
import com.harystolho.wetter.util.DateUtils
import com.harystolho.wetter.util.DefaultSingleObserver
import com.harystolho.wetter.util.Event
import com.harystolho.wetter.util.extension.mutableLiveData
import com.harystolho.wetter.util.extension.observe

class ForecastViewModel(private val weatherForecastRepository: WeatherForecastRepository) :
    ViewModel() {

    private val _weather = MutableLiveData<WeatherForecast>()

    val weather = Transformations.map(_weather) { WeatherForecastModel.fromEntity(it) }

    val isLoading = mutableLiveData(false)
    val isError = mutableLiveData(Event(false))

    fun loadWeatherForecast(cityId: Int) {
        isLoading.value = true

        weatherForecastRepository.getForecastForCity(cityId, 2)
            .observe(object : DefaultSingleObserver<List<WeatherForecast>>() {
                override fun onSuccess(forecasts: List<WeatherForecast>) {
                    isLoading.value = false

                    if (forecasts.size < 2) {
                        isError.value = Event(true)
                        return
                    }

                    _weather.value = forecasts[1]
                }

                override fun onError(e: Throwable) {
                    isLoading.value = false
                    isError.value = Event(true)
                }
            })
    }

}

class WeatherForecastModel {
    var cityName: String = ""
    var forecastDate: String = ""
    var minMaxTemp: String = ""
    var appMinMaxTemp: String = ""
    var wind: String = ""
    var timezone: String = ""
    var precipitation: String = ""
    var precipitationVolume: String = ""
    var humidity: String = ""
    var clouds: String = ""
    var weather: Weather = Weather.HOT

    enum class Weather {
        HOT, COLD, RAINY
    }

    companion object {
        fun fromEntity(forecast: WeatherForecast): WeatherForecastModel {
            return WeatherForecastModel().apply {
                cityName = forecast.cityName
                forecastDate = DateUtils.calendarToBrazilianDateFormat(forecast.forecastDate)
                minMaxTemp =
                    forecast.minTemp.toString().plus("° - ").plus(forecast.maxTemp).plus("°")
                appMinMaxTemp =
                    forecast.appMinTem.toString().plus("° - ").plus(forecast.appMaxTemp).plus("°")
                wind =
                    forecast.windSpeed.toInt().toString().plus(" m/s ").plus(forecast.windDirection)
                timezone = forecast.timezone.replace("_", " ")
                precipitation = forecast.precipitation.toInt().toString().plus("%")
                precipitationVolume = forecast.precipitationVolume.toInt().toString().plus("mm")
                humidity = forecast.relativeHumidity.toInt().toString().plus("%")
                clouds = forecast.cloudsPercentage.toInt().toString().plus("%")

                weather = calculateWeather(forecast)
            }
        }

        private fun calculateWeather(weatherForecast: WeatherForecast): Weather {
            return when {
                weatherForecast.precipitation > 80 -> Weather.RAINY
                weatherForecast.maxTemp > 20 -> Weather.HOT
                else -> Weather.COLD
            }
        }
    }
}