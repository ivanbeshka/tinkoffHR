package com.tinkoff.hr.view.map

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import com.tinkoff.hr.R
import com.tinkoff.hr.domain.Place
import com.tinkoff.hr.databinding.ItemAutocompletePlaceBinding
import java.util.*

class PlacesAdapter(
    context: Context,
    private val data: MutableList<Place>,
    private val listener: PlacesAutocompleteListener
) : ArrayAdapter<Place>(context, R.layout.item_autocomplete_place, data) {

    private val filteredData = mutableListOf<Place>()

    override fun getCount(): Int {
        return filteredData.size
    }

    override fun getFilter(): Filter {
        return PlacesFilter(this, data)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val binding = ItemAutocompletePlaceBinding.inflate(inflater, parent, false)

        val place = filteredData[position]
        binding.place = place

        binding.layout.setOnClickListener {
            listener.onPlaceClick(place)
        }

        return binding.root
    }

    fun setData(places: List<Place>){
        clear()
        addAll(places)
    }

    private fun setFilteredResults(places: List<Place>) {
        filteredData.clear()
        filteredData.addAll(places)
        notifyDataSetChanged()

        listener.onPlacesShow(places.isNotEmpty())
    }

    private class PlacesFilter(
        private val adapter: PlacesAdapter,
        private val originalData: List<Place>
    ) : Filter() {

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val results = FilterResults()

            val filteredData = if (constraint.isNullOrEmpty()) {
                originalData
            } else {
                val filterPattern = constraint.toString().trim().lowercase(Locale.getDefault())
                originalData.filter { it.name.lowercase(Locale.getDefault()).contains(filterPattern) }
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