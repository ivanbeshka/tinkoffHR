package com.tinkoff.hr.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinkoff.hr.api.FiltersApi
import com.tinkoff.hr.data.Filter
import com.tinkoff.hr.data.Filter.Companion.FILTER_ALL_POSITION
import com.tinkoff.hr.repository.FiltersRepository
import com.tinkoff.hr.viewmodels.common.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class FiltersViewModel : RxViewModel() {

    private val repository = FiltersRepository(FiltersApi())

    private val filters = MutableLiveData<ScreenState<List<Filter>>>()

    fun getFilters(): LiveData<ScreenState<List<Filter>>> {
        repository.getFilters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                filters.value = LoadingScreenState()
            }
            .subscribeBy(
                onSuccess = {
                    filters.value = SuccessScreenState(it)
                },
                onError = {
                    filters.value = ErrorScreenState(it)
                }
            )
            .disposeOnFinish()
        return filters
    }

    fun setFilterIsSelected(isSelected: Boolean, position: Int) {
        if (filters.value is SuccessScreenState) {
            val filters = (this.filters.value as SuccessScreenState).data.map { it.copy() }

            if (isSelected && position == FILTER_ALL_POSITION) {
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

            this.filters.value = SuccessScreenState(filters)
        }
    }

}