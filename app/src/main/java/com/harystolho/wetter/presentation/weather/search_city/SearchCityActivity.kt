package com.harystolho.wetter.presentation.weather.search_city

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.harystolho.wetter.R
import com.harystolho.wetter.databinding.ActivitySearchCityBinding
import com.harystolho.wetter.presentation.weather.search_city.adapter.CityAdapter
import com.harystolho.wetter.util.BaseActivity
import kotlinx.android.synthetic.main.activity_search_city.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchCityActivity : BaseActivity() {

    private val viewModel by viewModel<SearchCityViewModel>()

    private lateinit var cityAdapter: CityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivitySearchCityBinding>(
            this, R.layout.activity_search_city
        ).apply {
            viewModel = this@SearchCityActivity.viewModel
            lifecycleOwner = this@SearchCityActivity
        }

        setupAdapter()
        observeViewModel()

        viewModel.loadCities()
    }

    private fun observeViewModel() {
        viewModel.cities.observe(this, Observer { cities ->
            cityAdapter.setItems(cities)
        })
    }

    private fun setupAdapter() {
        cityAdapter = CityAdapter(this, mutableListOf())
        search_city_input.setAdapter(cityAdapter)
    }

}