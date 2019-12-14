package com.harystolho.wetter.di

import com.harystolho.wetter.presentation.weather.search_city.SearchCityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val wetterInject = module {

    viewModel { SearchCityViewModel() }
}