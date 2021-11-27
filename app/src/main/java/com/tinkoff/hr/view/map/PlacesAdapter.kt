package com.tinkoff.hr.view.map

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import com.tinkoff.hr.R
import com.tinkoff.hr.data.Place
import com.tinkoff.hr.databinding.ItemAutocompletePlaceBinding
import java.util.*

class PlacesAdapter(
    context: Context,
    private val data: List<Place>,
    private val listener: PlacesAutocompleteListener
) : ArrayAdapter<Place>(context, R.layout.item_autocomplete_place, data) {

    private val filteredData = mutableListOf<Place>()

    init {
        filteredData.addAll(data.map { it.copy() })
    }

    override fun getCount(): Int {
        return filteredData.size
    }

    override fun getFilter(): Filter {
        return PlacesFilter(this, data)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val place = filteredData[position]
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            ItemAutocompletePlaceBinding.inflate(inflater, parent, false)
        binding.place = place

        return binding.root
    }

    private fun setFilteredResults(places: List<Place>) {
        filteredData.clear()
        filteredData.addAll(places)
        notifyDataSetChanged()
        if (places.isNotEmpty()) {
            listener.onPlacesShow(true)
        } else {
            listener.onPlacesShow(false)
        }
    }

    private class PlacesFilter(
        private val adapter: PlacesAdapter,
        private val originalData: List<Place>
    ) : Filter() {
        val filteredData = mutableListOf<Place>()

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            filteredData.clear()
            val results = FilterResults()
            if (constraint == null || constraint.isEmpty()) {
                filteredData.addAll(originalData)
            } else {
                val filterPattern = constraint.toString().lowercase(Locale.getDefault()).trim()
                for (place in originalData) {
                    if (place.name.lowercase(Locale.getDefault()).contains(filterPattern)) {
                        filteredData.add(place)
                    }
                }
            }
            results.values = filteredData
            results.count = filteredData.size
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            adapter.setFilteredResults(results!!.values as List<Place>)
        }
    }

    interface PlacesAutocompleteListener {
        fun onPlacesShow(isVisible: Boolean)
        fun onPlaceClick(place: Place)
    }
}