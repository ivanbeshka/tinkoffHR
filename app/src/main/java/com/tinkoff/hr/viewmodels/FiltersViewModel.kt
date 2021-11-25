package com.tinkoff.hr.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinkoff.hr.data.Filter
import com.tinkoff.hr.data.Filter.Companion.FILTER_ALL_POSITION

class FiltersViewModel : ViewModel() {
    private val filters: MutableLiveData<List<Filter>> by lazy {
        MutableLiveData<List<Filter>>().also {
            it.value = listOf(
                Filter("Всё", true),
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

    fun setFilterIsSelected(isSelected: Boolean, position: Int) {
        val filters = this.filters.value!!.map { it.copy() }

        if (isSelected && position == FILTER_ALL_POSITION){
            for (i in 1 until filters.size) {
                filters[i].isSelected = false
            }
            filters[FILTER_ALL_POSITION].isSelected = isSelected
        } else {
            filters[FILTER_ALL_POSITION].isSelected = false
            filters[position].isSelected = isSelected

            if (filters.all { !it.isSelected }){
                filters[FILTER_ALL_POSITION].isSelected = true
            }
        }

        this.filters.value = filters
    }

}