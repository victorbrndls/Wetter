package com.harystolho.wetter.di

import com.harystolho.wetter.core.repository.CityRepository
import com.harystolho.wetter.core.repository.WeatherForecastRepository
import com.harystolho.wetter.infrastructure.DefaultRetrofitClient
import com.harystolho.wetter.infrastructure.JsonCityRepositoryImpl
import com.harystolho.wetter.infrastructure.WeatherBitWeatherForecastRepositoryImpl
import com.harystolho.wetter.presentation.weather.search_city.SearchCityViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val wetterInject = module {

    val retrofitClient = DefaultRetrofitClient()

    single<CityRepository> { JsonCityRepositoryImpl(androidContext()) }
    single<WeatherForecastRepository> {
        WeatherBitWeatherForecastRepositoryImpl(
            retrofitClient.weatherBitRetrofit.create(WeatherBitWeatherForecastRepositoryImpl.WeatherBitApi::class.java)
        )
    }


    viewModel { SearchCityViewModel(get()) }
}