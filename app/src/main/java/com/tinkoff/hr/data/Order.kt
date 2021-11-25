package com.tinkoff.hr.data

data class Order(
    val id: Int,
    val article: String,
    val name: String,
    val count: Int,
    val categories: List<String>,
    var selected: Boolean = false,
    val photoUrl: String? = null
)