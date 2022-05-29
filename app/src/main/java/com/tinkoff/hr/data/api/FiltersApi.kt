package com.tinkoff.hr.data.api

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tinkoff.hr.data.api.common.createSingleForTask
import com.tinkoff.hr.data.entities.FilterPojo
import io.reactivex.Single

class FiltersApi {
    private val filtersCollection = Firebase.firestore.collection(FILTERS_PATH)

    fun getFilters(): Single<List<FilterPojo>> {
        return createSingleForTask(
            taskBuilder = { filtersCollection.get() },
            valueBuilder = { querySnapshot -> querySnapshot.toObjects(FilterPojo::class.java) }
        )
    }

    private companion object {
        private const val FILTERS_PATH = "filters"
    }
}