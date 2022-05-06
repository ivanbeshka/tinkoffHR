package com.tinkoff.hr.repository

import com.tinkoff.hr.api.OrdersApi
import com.tinkoff.hr.data.Filter
import com.tinkoff.hr.data.Order
import io.reactivex.Single

class OrdersRepository(private val api: OrdersApi) {

    fun getAllOrders(): Single<List<Order>> = api.getOrders()

    fun getOrdersByFilter(filters: List<Filter>): Single<List<Order>> {
        return api.getOrders()
            .map { orders ->
                val selectedCategories = filters.filter { it.isSelected }.map { it.name }
                if (!selectedCategories.contains(Filter.FILTER_ALL_NAME)) {
                    return@map orders.filter { order ->
                        order.categories.any {
                            selectedCategories.contains(it)
                        }
                    }
                }
                orders
            }
    }
}