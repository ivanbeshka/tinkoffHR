package com.tinkoff.hr.data.api

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tinkoff.hr.data.entities.FilterPojo
import io.reactivex.Single

class FiltersApi {
    private val filtersCollection = Firebase.firestore.collection(FILTERS_PATH)

    fun getFilters(): Single<List<FilterPojo>> {
        return Single.create { emitter ->
            filtersCollection.get()
                .addOnSuccessListener {
                    val filters = mutableListOf<FilterPojo>()
                    it.forEach { document ->
                        val filter = document.toObject(FilterPojo::class.java)
                        filters.add(filter)
                    }
                    emitter.onSuccess(filters)
                }
                .addOnFailureListener {
                    emitter.onError(it)
                }
        }
    }

    private companion object {
        private const val FILTERS_PATH = "filters"
    }
}