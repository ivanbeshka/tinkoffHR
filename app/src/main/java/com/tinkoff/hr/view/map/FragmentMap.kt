package com.tinkoff.hr.view.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.tinkoff.hr.R
import com.tinkoff.hr.data.Place
import com.tinkoff.hr.databinding.FragmentMapBinding

class FragmentMap : Fragment(), OnMapReadyCallback, PlacesAdapter.PlacesAutocompleteListener {

    private lateinit var binding: FragmentMapBinding
    private val places = listOf(
        Place(
            "А ты где",
            "да",
            "200р",
            "",
            "",
            "",
            "10",
            LatLng(56.833826, 60.595112)
        ),
        Place(
            "Барашка на гранате",
            "да",
            "200р",
            "",
            "",
            "",
            "9",
            LatLng(56.840183, 60.593243)
        ),
        Place(
            "Бухара",
            "нет",
            "250-300р",
            "",
            "",
            "",
            "4",
            LatLng(56.836573, 60.594536)
        ),
        Place(
            "Рататуй",
            "да",
            "350р",
            "",
            "",
            "",
            "7,5",
            LatLng(56.833512, 60.593203)
        ),
        Place(
            "Friends",
            "да",
            "300р",
            "",
            "",
            "",
            "8",
            LatLng(56.827993, 60.598362)
        ),
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(inflater, container, false)

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val autocomplete = binding.autocompletePlace
        val placesAdapter = PlacesAdapter(requireContext(), places, this)
        autocomplete.setAdapter(placesAdapter)

        autocomplete.setOnDismissListener {
            binding.layoutPlaceValues.visibility = View.GONE
        }

        return binding.root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.uiSettings.isCompassEnabled = false

        places.forEach {
            googleMap.addMarker(
                MarkerOptions()
                    .position(it.latLng)
                    .title(it.name)
            )
        }

        googleMap.moveCamera(CameraUpdateFactory.zoomTo(15f))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(places[0].latLng))

    }

    override fun onPlacesShow(isVisible: Boolean) {
        if (isVisible && binding.autocompletePlace.isPopupShowing) {
            binding.layoutPlaceValues.visibility = View.VISIBLE
            binding.autocompletePlace.showDropDown()
        } else {
            binding.layoutPlaceValues.visibility = View.GONE
        }
    }

    override fun onPlaceClick(place: Place) {
    }
}