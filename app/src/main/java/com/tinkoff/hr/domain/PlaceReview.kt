package com.tinkoff.hr.domain

data class PlaceReview(
    val userId: String,
    val price: Int?,
    val rating: Int?,
    val pluses: String,
    val minuses: String
)
