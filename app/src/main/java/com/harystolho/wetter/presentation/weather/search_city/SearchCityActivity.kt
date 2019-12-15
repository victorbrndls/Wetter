package com.harystolho.wetter.presentation.weather.search_city

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.harystolho.wetter.R
import com.harystolho.wetter.databinding.ActivitySearchCityBinding
import com.harystolho.wetter.presentation.weather.forecast.ForecastActivity
import com.harystolho.wetter.presentation.weather.search_city.adapter.CityAdapter
import com.harystolho.wetter.util.presentation.BaseActivity
import com.harystolho.wetter.util.presentation.NoInternetError
import com.harystolho.wetter.util.presentation.UnknownError
import com.harystolho.wetter.util.extension.editableOf
import com.harystolho.wetter.util.extension.setOnSafeClickListener
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
        initViews()
        observeViewModel()

        viewModel.loadCities()
    }

    private fun initViews() {
        search_city_input.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.navigateToForecastView()
                return@setOnEditorActionListener true
            }

            false
        }

        search_city_input.onCitySelected = { city ->
            viewModel.selectCityAction(city)
        }

        search_city_input.addTextChangedListener { text ->
            if (text.isNullOrEmpty()) {
                viewModel.selectCityAction(null)
                search_city_input_clear.visibility = View.GONE
            } else {
                search_city_input_clear.visibility = View.VISIBLE
            }
        }

        search_city_input_clear.setOnSafeClickListener {
            search_city_input.text = editableOf("")
        }
    }

    private fun observeViewModel() {
        viewModel.cities.observe(this, Observer { cities ->
            cityAdapter.setItems(cities)
        })

        viewModel.isNavigateToForecastView.observe(this, Observer {
            it?.getContentIfNotHandled()?.let { cityId ->
                startActivity(ForecastActivity.intent(this, cityId))
            }
        })

        viewModel.isError.observe(this, Observer {
            it?.getContentIfNotHandled()?.let { error ->
                when (error) {
                    SearchCityError.NO_INTERNET_CONNECTION -> showMessage(NoInternetError())
                    SearchCityError.UNKNONWN -> showMessage(UnknownError())
                }
            }
        })
    }

    private fun setupAdapter() {
        cityAdapter = CityAdapter(this, mutableListOf())
        search_city_input.setAdapter(cityAdapter)
    }

}