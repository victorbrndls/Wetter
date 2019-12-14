package com.harystolho.wetter.presentation.weather.search_city.auto_complete

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import com.harystolho.wetter.core.domain.City

class CityAutoCompleteTextView : AppCompatAutoCompleteTextView {
    constructor(context: Context) : super(context)
    constructor(context: Context, set: AttributeSet) : super(context, set)

    /**
     * [getOnItemSelectedListener] doesn't work with [AppCompatAutoCompleteTextView]
     */
    var onCitySelected: ((City) -> Unit)? = null

    override fun convertSelectionToString(selectedItem: Any?): CharSequence {
        return if (selectedItem is City) {
            onCitySelected?.invoke(selectedItem)
            selectedItem.getDisplayText()
        } else
            super.convertSelectionToString(selectedItem)
    }
}