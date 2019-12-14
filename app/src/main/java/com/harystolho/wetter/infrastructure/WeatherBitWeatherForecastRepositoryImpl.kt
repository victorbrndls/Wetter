package com.harystolho.wetter.infrastructure

import com.harystolho.wetter.BuildConfig
import com.harystolho.wetter.core.repository.WeatherForecastRepository
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

class WeatherBitWeatherForecastRepositoryImpl(private val weatherBitApi: WeatherBitApi) :
    WeatherForecastRepository {

    override fun getForecastForCity(cityId: Int, days: Int): Single<Unit> {
        return weatherBitApi.getForecastForCity(cityId, days, key = BuildConfig.WEATHER_BIT_API_KEY)
    }

    interface WeatherBitApi {

        @GET("/forecast/daily?city_id={id}&days={days}&lang=pt&key={key}")
        fun getForecastForCity(
            @Path("id") cityId: Int,
            @Path("days") days: Int,
            @Path("key") key: String
        ): Single<Unit>

    }

}

