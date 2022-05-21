package com.tinkoff.hr.repository

import com.tinkoff.hr.data.api.FiltersApi
import com.tinkoff.hr.domain.Filter
import com.tinkoff.hr.domain.converters.toDomainFilters
import io.reactivex.Single

class FiltersRepository(private val api: FiltersApi) {

    fun getFilters(): Single<List<Filter>> = api.getFilters()
        .map {
            it.toDomainFilters()
        }
}