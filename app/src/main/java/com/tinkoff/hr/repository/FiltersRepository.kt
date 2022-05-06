package com.tinkoff.hr.repository

import com.tinkoff.hr.api.FiltersApi
import com.tinkoff.hr.data.Filter
import io.reactivex.Single

class FiltersRepository(private val api: FiltersApi) {

    fun getFilters(): Single<List<Filter>> = api.getFilters()
}