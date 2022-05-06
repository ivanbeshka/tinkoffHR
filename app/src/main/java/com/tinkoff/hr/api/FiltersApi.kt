package com.tinkoff.hr.api

import com.tinkoff.hr.data.Filter
import io.reactivex.Single

class FiltersApi {
    private val filters = listOf(
        Filter("Всё", true),
        Filter("Бакалея"),
        Filter("Молочные продукты"),
        Filter("Печеньки"),
        Filter("Кофе"),
        Filter("Выпечка")
    )

    fun getFilters(): Single<List<Filter>> = Single.just(filters)
}