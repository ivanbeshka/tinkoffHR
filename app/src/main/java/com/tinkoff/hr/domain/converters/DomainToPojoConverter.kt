package com.tinkoff.hr.domain.converters

import com.tinkoff.hr.data.entities.PlaceReviewPojo
import com.tinkoff.hr.domain.PlaceReview

fun PlaceReview.toPojoReview(placeId: String): PlaceReviewPojo {
    return PlaceReviewPojo(
        cafeId = placeId,
        userId = userId,
        minuses = minuses,
        pluses = pluses,
        price = price,
        rating = rating
    )
}