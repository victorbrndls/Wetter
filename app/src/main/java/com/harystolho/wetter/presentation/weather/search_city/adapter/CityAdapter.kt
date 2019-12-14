package com.harystolho.wetter.presentation.weather.search_city.adapter

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter
import androidx.annotation.LayoutRes
import com.harystolho.wetter.core.domain.City

class CityAdapter(
    context: Context, @LayoutRes resource: Int, private val cities: MutableList<City>
) :
    ArrayAdapter<City>(context, resource, cities.toMutableList()) { // Pass a copy of [city]

    fun setItems(newCities: List<City>) {
        cities.clear()
        cities.addAll(newCities)

        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                results ?: return

                clear()
                addAll(results.values as List<City>)
            }

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredItems = cities.filter {
                    it.name.contains(constraint.toString(), true) // TODO: ignore accents
                }

                return FilterResults().apply {
                    values = filteredItems
                    count = filteredItems.size
                }
            }
        }
    }

}