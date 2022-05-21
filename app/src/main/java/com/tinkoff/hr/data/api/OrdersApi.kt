package com.tinkoff.hr.data.api

import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tinkoff.hr.data.entities.ProductPojo
import io.reactivex.Single
import io.reactivex.SingleEmitter

class OrdersApi {

    private val productsCollection = Firebase.firestore.collection(PRODUCTS_PATH)

    fun getProducts(): Single<List<ProductPojo>> {
        return Single.create { emitter ->
            productsCollection.get()
                .addOnSuccessListener {
                    onSuccessProducts(it, emitter)
                }
                .addOnFailureListener {
                    emitter.onError(it)
                }
        }
    }

    fun getProductsWithFilters(filterIds: List<String>): Single<List<ProductPojo>> {
        return Single.create { emitter ->
            productsCollection.whereArrayContainsAny("filter_ids", filterIds)
                .get()
                .addOnSuccessListener {
                    onSuccessProducts(it, emitter)
                }
                .addOnFailureListener {
                    emitter.onError(it)
                }

        }
    }

    private fun onSuccessProducts(
        snapshot: QuerySnapshot,
        emitter: SingleEmitter<List<ProductPojo>>
    ) {
        val products = mutableListOf<ProductPojo>()
        snapshot.forEach { document ->
            val product = document.toObject(ProductPojo::class.java)
            products.add(product)
        }
        emitter.onSuccess(products)
    }

    private companion object {
        private const val PRODUCTS_PATH = "products"
    }
}