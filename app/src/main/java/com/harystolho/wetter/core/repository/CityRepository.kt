package com.harystolho.wetter.core.repository

import com.harystolho.wetter.core.domain.City
import io.reactivex.Single

interface CityRepository {

    /**
     * @throws [com.harystolho.wetter.core.exception.ResourceReadException] if the cities can't be loaded
     */
    fun getCities(): Single<List<City>>

}