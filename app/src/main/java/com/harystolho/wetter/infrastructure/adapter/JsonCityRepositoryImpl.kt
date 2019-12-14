package com.harystolho.wetter.infrastructure.adapter

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import com.harystolho.wetter.R
import com.harystolho.wetter.core.domain.City
import com.harystolho.wetter.core.exception.ResourceReadException
import com.harystolho.wetter.core.repository.CityRepository
import io.reactivex.Single
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class JsonCityRepositoryImpl(private val context: Context) : CityRepository {

    override fun getCities(): Single<List<City>> {
        return readCities().map {
            try {
                val cities = Gson().fromJson<List<CityData>>(
                    it, TypeToken.getParameterized(ArrayList::class.java, CityData::class.java).type
                )

                cities.map(CityData::toEntity)
            } catch (e: JsonSyntaxException) {
                throw ResourceReadException("Error converting json file to CityData list", e)
            }
        }
    }

    private fun readCities(): Single<String> {
        return Single.create { emitter ->
            Log.d(TAG, "Reading cities from R.raw.br_cities")

            try {
                val inputStream = context.resources.openRawResource(R.raw.br_cities)
                val br = BufferedReader(InputStreamReader(inputStream))
                val sb = StringBuilder()

                br.use {
                    var line: String? = br.readLine()

                    while (line != null) {
                        sb.append(line)
                        line = br.readLine()
                    }
                }

                emitter.onSuccess(sb.toString())
            } catch (e: IOException) {
                emitter.onError(ResourceReadException("Error reading cities from json file", e))
            }
        }
    }

    inner class CityData(
        val id: Int,
        val name: String,
        val state: String
    ) {
        fun toEntity() = City(
            id,
            name,
            state
        )
    }

    companion object {
        private const val TAG = "JsonCityRepositoryImpl"
    }

}