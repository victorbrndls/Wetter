package com.harystolho.wetter.di

import com.harystolho.wetter.core.repository.CityRepository
import com.harystolho.wetter.core.repository.WeatherForecastRepository
import com.harystolho.wetter.core.service.NetworkService
import com.harystolho.wetter.infrastructure.DefaultRetrofitClient
import com.harystolho.wetter.infrastructure.JsonCityRepositoryImpl
import com.harystolho.wetter.infrastructure.NetworkServiceImpl
import com.harystolho.wetter.infrastructure.weather_bit.WeatherBitWeatherForecastRepositoryImpl
import com.harystolho.wetter.presentation.weather.forecast.ForecastViewModel
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
    single<NetworkService> { NetworkServiceImpl(androidContext()) }

    viewModel { SearchCityViewModel(get(), get()) }
    viewModel { ForecastViewModel(get()) }
}