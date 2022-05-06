package com.tinkoff.hr.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tinkoff.hr.api.OrdersApi
import com.tinkoff.hr.data.Filter
import com.tinkoff.hr.data.Filter.Companion.FILTER_ALL_NAME
import com.tinkoff.hr.data.Order
import com.tinkoff.hr.repository.OrdersRepository
import com.tinkoff.hr.viewmodels.common.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class OrdersViewModel : RxViewModel() {

    private val repository = OrdersRepository(OrdersApi())

    private val orders = MutableLiveData<ScreenState<List<Order>>>()

    fun getOrders(): LiveData<ScreenState<List<Order>>> {
        repository.getAllOrders()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { orders.value = LoadingScreenState() }
            .subscribeBy(
                onSuccess = {
                    orders.value = SuccessScreenState(it)
                },
                onError = {
                    orders.value = ErrorScreenState(it)
                }
            )
            .disposeOnFinish()

        return orders
    }

    fun setIsSelected(orderId: Int, isSelected: Boolean) {
        if (orders.value is SuccessScreenState) {
            (orders.value as SuccessScreenState).data.find { it.id == orderId }?.selected = isSelected
        }
    }

    fun setFilters(filters: List<Filter>) {
        repository.getOrdersByFilter(filters)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { orders.value = LoadingScreenState() }
            .subscribeBy(
                onSuccess = {
                    orders.value = SuccessScreenState(it)
                },
                onError = {
                    orders.value = ErrorScreenState(it)
                }
            )
            .disposeOnFinish()
    }
}