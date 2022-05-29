package com.tinkoff.hr.domain

data class Filter(
    val id: String,
    val name: String,
    var isSelected: Boolean = false
) {
    companion object {
        const val FILTER_ALL_POSITION = 0
        const val FILTER_ALL_NAME = "Всё"
        const val FILTER_ALL_ID = "1234567890"

        fun allFilter(): Filter {
            return Filter(
                FILTER_ALL_ID,
                FILTER_ALL_NAME,
                true
            )
        }
    }
}