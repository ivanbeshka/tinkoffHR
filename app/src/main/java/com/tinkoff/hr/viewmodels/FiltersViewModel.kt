package com.tinkoff.hr.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinkoff.hr.data.Filter

class FiltersViewModel : ViewModel() {
    private val filters: MutableLiveData<List<Filter>> by lazy {
        MutableLiveData<List<Filter>>().also {
            it.value = listOf(
                Filter("Всё"),
                Filter("Бакалея"),
                Filter("Молочные продукты"),
                Filter("Печеньки"),
                Filter("Кофе"),
                Filter("Выпечка"),
                Filter("Всё"),
                Filter("Бакалея"),
                Filter("Молочные продукты"),
                Filter("Печеньки"),
                Filter("Кофе"),
                Filter("Выпечка")
            )
        }
    }

    fun getFilters(): LiveData<List<Filter>> {
        return filters
    }

    fun setFilterIsChecked(isChecked: Boolean, position: Int) {
        filters.value?.get(position)?.isChecked = isChecked
    }
}