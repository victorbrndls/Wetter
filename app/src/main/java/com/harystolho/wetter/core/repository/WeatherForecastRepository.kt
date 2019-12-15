package com.harystolho.wetter.core.repository

import com.harystolho.wetter.core.domain.WeatherForecast
import io.reactivex.Single

interface WeatherForecastRepository {

    /**
     * @param days number of forecasts, each day is 1 forecast. The first forecast is for the current day.
     *             To get the forecast for tomorrow, 'days' has to be 2
     *
     * @throws [com.harystolho.wetter.core.exception.ResourceReadException] if the forecast can't be loaded
     */
    fun getForecastForCity(cityId: Int, days: Int): Single<List<WeatherForecast>>

}