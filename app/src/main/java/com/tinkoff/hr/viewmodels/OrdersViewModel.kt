package com.tinkoff.hr.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tinkoff.hr.data.api.OrdersApi
import com.tinkoff.hr.domain.Filter
import com.tinkoff.hr.domain.Product
import com.tinkoff.hr.repository.OrdersRepository
import com.tinkoff.hr.viewmodels.common.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class OrdersViewModel : RxViewModel() {

    private val repository = OrdersRepository(OrdersApi())

    private val orders = MutableLiveData<ScreenState<List<Product>>>()

    fun getOrders(): LiveData<ScreenState<List<Product>>> {
        repository.getAllProducts()
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

    fun setIsSelected(productId: String, isSelected: Boolean) {
        if (orders.value is SuccessScreenState) {
            (orders.value as SuccessScreenState).data.find { it.id == productId }?.selected = isSelected
        }
    }

    fun setFilters(filters: List<Filter>) {
        val selectedFilters = filters.filter { it.isSelected }

        repository.getProductsByFilter(selectedFilters)
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