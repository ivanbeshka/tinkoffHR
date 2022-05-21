package com.tinkoff.hr.domain

import com.google.android.gms.maps.model.LatLng

data class Place(
    val id: String,
    val name: String,
    val lunch: String,
    val averageCheck: String,
    val rating: String,
    val latLng: LatLng,
    val address: String,
    val reviews: List<PlaceReview>
)
