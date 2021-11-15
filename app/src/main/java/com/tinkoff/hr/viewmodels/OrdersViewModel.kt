package com.tinkoff.hr.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinkoff.hr.data.Order

class OrdersViewModel : ViewModel() {
    private val orders: MutableLiveData<List<Order>> by lazy {
        MutableLiveData<List<Order>>().also {
            it.value = listOf(
                Order(1, "", "Сушки FINE LIFE Малютка с маком, 350 г", 199, false),
                Order(2, "", "Сушки FINE LIFE Малютка с маком, 350 г", 199, true),
                Order(3, "", "Сушки FINE LIFE Малютка с маком, 350 г", 199, false),
                Order(1, "", "Сушки FINE LIFE Малютка с маком, 350 г", 199, false),
                Order(2, "", "Сушки FINE LIFE Малютка с маком, 350 г", 199, true),
                Order(3, "", "Сушки FINE LIFE Малютка с маком, 350 г", 199, false),
                Order(1, "", "Сушки FINE LIFE Малютка с маком, 350 г", 199, false),
                Order(2, "", "Сушки FINE LIFE Малютка с маком, 350 г", 199, true),
                Order(3, "", "Сушки FINE LIFE Малютка с маком, 350 г", 199, false),
            )
        }
    }

    fun getOrders(): LiveData<List<Order>> = orders

    fun setIsSelected(position: Int, isSelected: Boolean){
        orders.value?.get(position)?.selected = isSelected
    }
}