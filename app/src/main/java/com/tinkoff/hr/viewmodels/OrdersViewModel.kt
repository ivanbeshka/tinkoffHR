package com.tinkoff.hr.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinkoff.hr.data.Filter
import com.tinkoff.hr.data.Filter.Companion.FILTER_ALL_NAME
import com.tinkoff.hr.data.Order

class OrdersViewModel : ViewModel() {
    private val getOrders = listOf(
        Order(1, "", "Сушки FINE LIFE Малютка с маком, 350 г", 199, listOf("Бакалея")),
        Order(2, "", "Молоко, 350 мл", 199, listOf("Молочные продукты")),
        Order(3, "", "Печеньки, 350 г", 199, listOf("Печеньки", "Бакалея")),
        Order(1, "", "Живой кофе Ирландский ликер зерно 1000 гр", 199, listOf("Кофе")),
        Order(2, "", "Кофе в зернах Lavazza Crema e Aroma, 1 кг", 199, listOf("Кофе")),
        Order(3, "", "Хлеб", 199, listOf("Выпечка")),
        Order(1, "", "Тоже хлеб", 199, listOf("Выпечка")),
        Order(2, "", "Сгущёнка", 199, listOf("Молочные продукты")),
        Order(3, "", "Йогурт", 199, listOf("Молочные продукты"))
    )

    private val orders: MutableLiveData<List<Order>> by lazy {
        MutableLiveData<List<Order>>().also {
            it.value = getOrders
        }
    }

    fun getOrders(): LiveData<List<Order>> = orders

    fun setIsSelected(position: Int, isSelected: Boolean) {
        orders.value?.get(position)?.selected = isSelected
    }

    fun setFilters(filters: List<Filter>) {
        val selectedFilters = filters.filter { it.isSelected }.map { it.name }

        if (selectedFilters.contains(FILTER_ALL_NAME)) {
            orders.value = getOrders
        } else {
            val filteredOrders = getOrders.filter { order ->
                order.categories.any { category ->
                    selectedFilters.contains(category)
                }
            }

            orders.value = filteredOrders.toList()
        }
    }
}