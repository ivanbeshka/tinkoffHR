package com.tinkoff.hr.data.entities

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName

data class FilterPojo(
    @DocumentId
    var id: String = "",

    @set:PropertyName("name")
    @get:PropertyName("name")
    var name: String = ""
)
