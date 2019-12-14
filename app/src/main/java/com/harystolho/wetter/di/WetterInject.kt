package com.harystolho.wetter.di

import com.harystolho.wetter.core.repository.CityRepository
import com.harystolho.wetter.infrastructure.adapter.JsonCityRepositoryImpl
import com.harystolho.wetter.presentation.weather.search_city.SearchCityViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val wetterInject = module {

    single<CityRepository> { JsonCityRepositoryImpl(androidContext()) }

    viewModel { SearchCityViewModel(get()) }
}