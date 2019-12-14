package com.harystolho.wetter.presentation.weather.search_city

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.harystolho.wetter.R
import com.harystolho.wetter.core.domain.City
import com.harystolho.wetter.databinding.ActivitySearchCityBinding
import com.harystolho.wetter.util.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchCityActivity : BaseActivity() {

    private val viewModel by viewModel<SearchCityViewModel>()

    private lateinit var cityAdapter : ArrayAdapter<City>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivitySearchCityBinding>(
            this, R.layout.activity_search_city
        ).apply {
            viewModel = this@SearchCityActivity.viewModel
            lifecycleOwner = this@SearchCityActivity
        }

        
    }

}