package com.tinkoff.hr.view.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.tinkoff.hr.R
import com.tinkoff.hr.data.Place
import com.tinkoff.hr.databinding.FragmentMapBinding
import com.tinkoff.hr.utils.showToast
import com.tinkoff.hr.view.map.place_description.PlaceBottomSheet
import com.tinkoff.hr.viewmodels.PlacesViewModel

class MapFragment : Fragment(), OnMapReadyCallback, PlacesAdapter.PlacesAutocompleteListener,
    GoogleMap.OnMarkerClickListener {

    private val placesViewModel: PlacesViewModel by viewModels()

    private lateinit var map: GoogleMap
    private lateinit var placesAdapter: PlacesAdapter

    private lateinit var binding: FragmentMapBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(inflater, container, false)

        setMapAsync()

        placesAdapter = PlacesAdapter(requireContext(), mutableListOf(), this)
        placesViewModel.getPlaces().observe(viewLifecycleOwner) { state ->
            state.on(
                success = {
                    placesAdapter.setData(it)
                },
                error = { throwable ->
                    throwable.message?.let {
                        showToast(it)
                    }
                }
            )
        }

        val autocomplete = binding.autocompletePlace
        autocomplete.setAdapter(placesAdapter)
        autocomplete.setOnDismissListener {
            binding.layoutPlaceValues.visibility = View.GONE
        }

        return binding.root
    }

    private fun setMapAsync() {
        (childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment)
            .getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        map.uiSettings.isCompassEnabled = false

        map.setOnMarkerClickListener(this)


        placesViewModel.getPlaces().observe(viewLifecycleOwner) { state ->
            state.on(
                success = { places ->
                    places.forEach {
                        map.addMarker(
                            MarkerOptions()
                                .position(it.latLng)
                                .title(it.name)
                        )
                    }
                },
                error = { throwable ->
                    throwable.message?.let {
                        showToast(it)
                    }
                }
            )
        }

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(TIN_LAT_LNG, ZOOM_ALL))
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
        binding.autocompletePlace.setText(place.name)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(place.latLng, ZOOM_PLACE))
        showBottomSheet(place)
    }

    override fun onMarkerClick(marker: Marker): Boolean {

        placesViewModel.getPlaces().observe(viewLifecycleOwner) {state ->
            state.on(
                success = { places ->
                    val place = places.firstOrNull { it.latLng == marker.position }
                    place?.let { showBottomSheet(place) }
                },
                error = { throwable ->
                    throwable.message?.let {
                        showToast(it)
                    }
                }
            )
        }

        return true
    }

    private fun showBottomSheet(place: Place) {
        PlaceBottomSheet(place).show(childFragmentManager, PlaceBottomSheet.TAG)
    }

    private companion object {
        val TIN_LAT_LNG = LatLng(56.835757195167155, 60.590647994248926)
        const val ZOOM_ALL = 15f
        const val ZOOM_PLACE = 17f
    }
}