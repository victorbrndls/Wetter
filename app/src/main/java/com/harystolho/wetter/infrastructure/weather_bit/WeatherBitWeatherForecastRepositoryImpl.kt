package com.harystolho.wetter.infrastructure.weather_bit

import android.util.Log
import com.harystolho.wetter.BuildConfig
import com.harystolho.wetter.core.domain.WeatherForecast
import com.harystolho.wetter.core.exception.ResourceReadException
import com.harystolho.wetter.core.repository.WeatherForecastRepository
import com.harystolho.wetter.infrastructure.weather_bit.data.WeatherForecastData
import com.harystolho.wetter.infrastructure.weather_bit.mapper.WeatherForecastDataMapper
import com.harystolho.wetter.util.Mapper
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

class WeatherBitWeatherForecastRepositoryImpl(private val weatherBitApi: WeatherBitApi) :
    WeatherForecastRepository {

    private val weatherForecastDataMapper = WeatherForecastDataMapper()

    override fun getForecastForCity(cityId: Int, days: Int): Single<List<WeatherForecast>> {
        Log.d(TAG, "Fetching $days days forecasts for $cityId")

        return weatherBitApi.getForecastForCity(
            cityId, days, key = BuildConfig.WEATHER_BIT_API_KEY
        ).map { weatherForecastDataMapper.map(it) }.doOnError {
            throw ResourceReadException("Error getting forecast", it)
        }
    }

    interface WeatherBitApi {

        @GET("/forecast/daily?city_id={id}&days={days}&lang=pt&key={key}")
        fun getForecastForCity(
            @Path("id") cityId: Int,
            @Path("days") days: Int,
            @Path("key") key: String
        ): Single<WeatherForecastData>

    }

    companion object {
        private const val TAG = "WeatherBitForecastRepo"
    }

}

