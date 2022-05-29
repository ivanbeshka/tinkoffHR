package com.tinkoff.hr.data.api

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tinkoff.hr.data.api.common.createSingleForTask
import com.tinkoff.hr.data.entities.ProductPojo
import io.reactivex.Single

class OrdersApi {

    private val productsCollection = Firebase.firestore.collection(PRODUCTS_PATH)

    fun getProducts(): Single<List<ProductPojo>> {
        return createSingleForTask(
            taskBuilder = { productsCollection.get() },
            valueBuilder = { querySnapshot -> querySnapshot.toObjects(ProductPojo::class.java) }
        )
    }

    fun getProductsWithFilters(filterIds: List<String>): Single<List<ProductPojo>> {
        return createSingleForTask(
            taskBuilder = {
                productsCollection.whereArrayContainsAny("filter_ids", filterIds).get()
            },
            valueBuilder = { querySnapshot -> querySnapshot.toObjects(ProductPojo::class.java) }
        )
    }

    private companion object {
        private const val PRODUCTS_PATH = "products"
    }
}