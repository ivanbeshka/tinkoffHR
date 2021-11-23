package com.tinkoff.hr.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinkoff.hr.data.Filter

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
        val mFilters = filters.value!!.map { it.copy() }

        if (isSelected && position == FILTER_ALL_POSITION){
            for (i in 1 until mFilters.size) {
                mFilters[i].isSelected = false
            }
            mFilters[FILTER_ALL_POSITION].isSelected = isSelected
        } else {
            mFilters[FILTER_ALL_POSITION].isSelected = false
            mFilters[position].isSelected = isSelected

            if (mFilters.all { !it.isSelected }){
                mFilters[FILTER_ALL_POSITION].isSelected = true
            }
        }

        filters.value = mFilters
    }

    private companion object {
        const val FILTER_ALL_POSITION = 0
    }
}