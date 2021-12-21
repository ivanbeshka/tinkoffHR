package com.tinkoff.hr.data

import com.google.android.gms.maps.model.LatLng

data class Place(
    val id: Int,
    val name: String,
    val lunch: String,
    val averagePrice: String,
    val rating: String,
    val latLng: LatLng,
    val address: String,
    val reviews: List<PlaceReview>
)
