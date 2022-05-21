package com.tinkoff.hr.repository

import com.tinkoff.hr.data.api.OrdersApi
import com.tinkoff.hr.domain.Filter
import com.tinkoff.hr.domain.Product
import com.tinkoff.hr.domain.converters.toDomainProducts
import io.reactivex.Single

class OrdersRepository(private val api: OrdersApi) {

    fun getAllProducts(): Single<List<Product>> = api.getProducts()
        .map {
            it.toDomainProducts()
        }

    fun getProductsByFilter(filters: List<Filter>): Single<List<Product>> {
        return if (filters.map { it.name }.contains(Filter.FILTER_ALL_NAME)) {
            getAllProducts()
        } else {
            api.getProductsWithFilters(filters.map { it.id })
                .map {
                    it.toDomainProducts()
                }
        }
    }
}