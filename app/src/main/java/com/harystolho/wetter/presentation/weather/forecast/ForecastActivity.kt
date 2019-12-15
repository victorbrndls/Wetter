package com.harystolho.wetter.presentation.weather.forecast

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.harystolho.wetter.R
import com.harystolho.wetter.databinding.ActivityWeatherForecastBinding
import com.harystolho.wetter.util.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForecastActivity : BaseActivity() {

    private val viewModel by viewModel<ForecastViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cityId = intent.extras?.getInt(CITY_ID_ARG) ?: run {
            Log.e(TAG, "The intent key '$CITY_ID_ARG' is required but it's null")
            finish()
            return
        }

        DataBindingUtil.setContentView<ActivityWeatherForecastBinding>(
            this, R.layout.activity_weather_forecast
        ).apply {
            viewModel = this@ForecastActivity.viewModel
            lifecycleOwner = this@ForecastActivity
        }

        viewModel.loadWeatherForecast(cityId)
    }

    companion object {
        private const val TAG = "ForecastActivity"

        private const val CITY_ID_ARG = "CITY_ID"

        fun intent(context: Context, cityId: Int): Intent {
            return Intent(context, ForecastActivity::class.java).apply {
                putExtra(CITY_ID_ARG, cityId)
            }
        }
    }

}