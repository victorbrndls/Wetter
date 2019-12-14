package com.harystolho.wetter.core.repository

import io.reactivex.Single

interface WeatherForecastRepository {

    fun getForecastForCity(cityId: Int, days: Int = 1): Single<Unit>

}