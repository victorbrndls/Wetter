package com.harystolho.wetter.presentation.weather.search_city.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import com.harystolho.wetter.R
import com.harystolho.wetter.core.domain.City
import kotlinx.android.synthetic.main.city_spinner_dropdown_item.view.*

class CityAdapter(context: Context, private val cities: MutableList<City>) :
    ArrayAdapter<City>(
        context,
        R.layout.city_spinner_dropdown_item,
        cities.toMutableList()
    ) {

    fun setItems(newCities: List<City>) {
        cities.clear()
        cities.addAll(newCities)

        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.city_spinner_dropdown_item,
            parent,
            false
        )

        getItem(position)?.let { city ->
            itemView.city_name.text = city.getDisplayText()
        }

        return itemView
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