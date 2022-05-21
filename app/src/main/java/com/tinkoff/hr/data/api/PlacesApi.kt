package com.tinkoff.hr.data.api

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.tinkoff.hr.data.entities.PlacePojo
import com.tinkoff.hr.data.entities.PlaceReviewPojo
import io.reactivex.Single

class PlacesApi {

    private val placesCollection = Firebase.firestore.collection(PLACES_PATH)
    private val placesReviewsCollection = Firebase.firestore.collection(REVIEWS_PATH)

    fun getPlaces(): Single<List<PlacePojo>> {
        return Single.create { emitter ->
            placesCollection.get()
                .addOnSuccessListener {
                    val places = mutableListOf<PlacePojo>()
                    it.forEach { document ->
                        val place = document.toObject(PlacePojo::class.java)
                        places.add(place)
                    }
                    emitter.onSuccess(places)
                }
                .addOnFailureListener {
                    emitter.onError(it)
                }
        }
    }

    fun getReviews(placeId: String): Single<List<PlaceReviewPojo>> {
        return Single.create { emitter ->
            placesReviewsCollection
                .whereEqualTo(PLACE_ID_FIELD, placeId)
                .get()
                .addOnSuccessListener {
                    val reviews = mutableListOf<PlaceReviewPojo>()
                    it.forEach { document ->
                        val review = document.toObject(PlaceReviewPojo::class.java)
                        reviews.add(review)
                    }
                    emitter.onSuccess(reviews)
                }
                .addOnFailureListener {
                    emitter.onError(it)
                }
        }
    }

    private companion object {
        private const val PLACES_PATH = "cafes"
        private const val REVIEWS_PATH = "cafe_reviews"
        private const val PLACE_ID_FIELD = "cafe_id"
    }
}