package com.tinkoff.hr.view.map

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.common.api.Status
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.tinkoff.hr.R
import com.tinkoff.hr.databinding.FragmentMapBinding

class FragmentMap : Fragment(), OnMapReadyCallback {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMapBinding.inflate(inflater, container, false)

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return binding.root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.uiSettings.isCompassEnabled = false


        val ekb = LatLng(56.833332, 60.583332)
        googleMap.addMarker(
            MarkerOptions()
                .position(ekb)
                .title("Ekb")
        )

        googleMap.moveCamera(CameraUpdateFactory.zoomTo(15f))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(ekb))

    }
}