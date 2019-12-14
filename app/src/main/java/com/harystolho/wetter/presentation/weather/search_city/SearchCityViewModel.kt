package com.harystolho.wetter.presentation.weather.search_city

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.harystolho.wetter.core.domain.City
import com.harystolho.wetter.util.Event
import com.harystolho.wetter.util.extension.mutableLiveData

class SearchCityViewModel : ViewModel() {

    private val _cities = MutableLiveData<List<City>>()

    val cities = Transformations.map(_cities) { it }

    val isError = mutableLiveData(Event(false))

    fun loadCities() {
        _cities.value = listOf(
            City(1, "CL", "Parana"),
            City(2, "CWB", "Parana"),
            City(3, "SP", "Sao Paulo"),
            City(4, "RJ", "Rio de Janeiro")
        )
    }


}