package com.tinkoff.hr.data.entities

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName

data class ProductPojo(
    @DocumentId
    var id: String = "",

    @set:PropertyName("name")
    @get:PropertyName("name")
    var name: String = "",

    @set:PropertyName("count")
    @get:PropertyName("count")
    var count: Int = 0,

    @set:PropertyName("article")
    @get:PropertyName("article")
    var article: Int = 0,

    @set:PropertyName("photo_url")
    @get:PropertyName("photo_url")
    var photoUrl: String? = null,

    @set:PropertyName("filter_ids")
    @get:PropertyName("filter_ids")
    var filterIds: List<String> = emptyList()
)
