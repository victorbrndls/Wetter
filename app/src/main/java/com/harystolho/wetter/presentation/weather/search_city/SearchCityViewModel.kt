package com.harystolho.wetter.presentation.weather.search_city

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.harystolho.wetter.core.domain.City
import com.harystolho.wetter.core.repository.CityRepository
import com.harystolho.wetter.util.DefaultSingleObserver
import com.harystolho.wetter.util.Event
import com.harystolho.wetter.util.extension.mutableLiveData
import com.harystolho.wetter.util.extension.observe

class SearchCityViewModel(private val cityRepository: CityRepository) : ViewModel() {

    private val _cities = MutableLiveData<List<City>>()
    val cities = Transformations.map(_cities) { it }

    private var selectedCity: City? = null

    val isNavigateToForecastView = mutableLiveData(Event<Int?>(null))
    val isError = mutableLiveData(Event(false))

    fun loadCities() {
        cityRepository.getCities().observe(object : DefaultSingleObserver<List<City>>() {
            override fun onSuccess(t: List<City>) {
                _cities.value = t
            }

            override fun onError(e: Throwable) {
                isError.value = Event(true)
            }
        })
    }

    fun selectCityAction(city: City?) {
        selectedCity = city
    }

    fun navigateToForecastView() {
        selectedCity ?: return

        isNavigateToForecastView.value = Event(selectedCity!!.id)
    }

}