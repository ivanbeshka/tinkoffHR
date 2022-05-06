package com.tinkoff.hr.api

import com.tinkoff.hr.data.Order
import io.reactivex.Single

class OrdersApi {

    private val orders = listOf(
        Order(1, "", "Сушки FINE LIFE Малютка с маком, 350 г", 199, listOf("Бакалея")),
        Order(2, "", "Молоко, 350 мл", 199, listOf("Молочные продукты")),
        Order(3, "", "Печеньки, 350 г", 199, listOf("Печеньки", "Бакалея")),
        Order(4, "", "Живой кофе Ирландский ликер зерно 1000 гр", 199, listOf("Кофе")),
        Order(5, "", "Кофе в зернах Lavazza Crema e Aroma, 1 кг", 199, listOf("Кофе")),
        Order(6, "", "Хлеб", 199, listOf("Выпечка")),
        Order(7, "", "Тоже хлеб", 199, listOf("Выпечка")),
        Order(8, "", "Сгущёнка", 199, listOf("Молочные продукты")),
        Order(9, "", "Йогурт", 199, listOf("Молочные продукты"))
    )

    fun getOrders(): Single<List<Order>> = Single.just(orders)
}