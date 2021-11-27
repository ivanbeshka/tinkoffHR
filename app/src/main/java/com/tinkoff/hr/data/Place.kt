package com.tinkoff.hr.data

import com.google.android.gms.maps.model.LatLng

data class Place(
    val name: String,
    val lunch: String,
    val averagePrice: String,
    val pluses: String,
    val minuses: String,
    val hair: String,
    val rating: String,
    val latLng: LatLng
)
