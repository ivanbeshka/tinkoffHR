package com.tinkoff.hr.data

data class Order(
    val id: Int,
    val article: String,
    val name: String,
    val count: Int,
    var selected: Boolean,
    val photoUrl: String? = null
)