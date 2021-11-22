package com.tinkoff.hr.viewmodels

import android.util.Log
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
        val mFilters = filters.value!!



        if (isSelected && position == 0){
            for (i in 1 until mFilters.size) {
                mFilters[i].isSelected = false
            }
            mFilters[0].isSelected = isSelected
        } else {
            mFilters[0].isSelected = false
            mFilters[position].isSelected = isSelected

            if (mFilters.all { !it.isSelected }){
                mFilters[0].isSelected = true
            }
        }

        filters.value = mFilters
    }
}