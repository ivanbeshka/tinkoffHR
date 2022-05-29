package com.tinkoff.hr.data.entities

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName

data class PlaceReviewPojo(
    @DocumentId
    var id: String = "",

    @set:PropertyName("cafe_id")
    @get:PropertyName("cafe_id")
    var cafeId: String = "",

    @set:PropertyName("user_id")
    @get:PropertyName("user_id")
    var userId: String = "",

    @set:PropertyName("minuses")
    @get:PropertyName("minuses")
    var minuses: String = "",

    @set:PropertyName("pluses")
    @get:PropertyName("pluses")
    var pluses: String = "",

    @set:PropertyName("price")
    @get:PropertyName("price")
    var price: Int? = null,

    @set:PropertyName("rating")
    @get:PropertyName("rating")
    var rating: Int? = null
)