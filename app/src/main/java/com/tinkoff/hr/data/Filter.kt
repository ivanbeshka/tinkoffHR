package com.tinkoff.hr.data

data class Filter(
    val name: String,
    var isSelected: Boolean = false
) {
    companion object {
        const val FILTER_ALL_POSITION = 0
        const val FILTER_ALL_NAME = "Всё"
    }
}