package com.tinkoff.hr.data.entities

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.PropertyName

data class PlacePojo(
    @DocumentId
    var id: String = "",

    @set:PropertyName("name")
    @get:PropertyName("name")
    var name: String = "",

    @set:PropertyName("address")
    @get:PropertyName("address")
    var address: String = "",

    @set:PropertyName("geolocation")
    @get:PropertyName("geolocation")
    var geolocation: GeoPoint? = null,

    @set:PropertyName("average_check")
    @get:PropertyName("average_check")
    var averageCheck: Float? = null,

    @set:PropertyName("business_lunch")
    @get:PropertyName("business_lunch")
    var businessLunch: String = ""
)