package com.tinkoff.hr.data.entities

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName

data class FAQPojo(
    @DocumentId
    var id: String = "",

    @set:PropertyName("content")
    @get:PropertyName("content")
    var content: String = "",

    @set:PropertyName("title")
    @get:PropertyName("title")
    var title: String = ""
)