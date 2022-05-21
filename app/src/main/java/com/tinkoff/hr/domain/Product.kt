package com.tinkoff.hr.domain

data class Product(
    val id: String,
    val article: Int,
    val name: String,
    val count: Int,
    val categories: List<String>,
    var selected: Boolean = false,
    val photoUrl: String? = null
)