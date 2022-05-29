package com.tinkoff.hr.domain

data class PlaceReview(
    val userId: String,
    val price: String,
    val rating: String,
    val pluses: String,
    val minuses: String
)
