package com.tinkoff.hr.data.api

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tinkoff.hr.data.api.common.createSingleForTask
import com.tinkoff.hr.data.entities.PlacePojo
import com.tinkoff.hr.data.entities.PlaceReviewPojo
import io.reactivex.Single

class PlacesApi {

    private val placesCollection = Firebase.firestore.collection(PLACES_PATH)
    private val placesReviewsCollection = Firebase.firestore.collection(REVIEWS_PATH)

    fun getPlaces(): Single<List<PlacePojo>> {
        return createSingleForTask(
            taskBuilder = { placesCollection.get() },
            valueBuilder = { querySnapshot -> querySnapshot.toObjects(PlacePojo::class.java) }
        )
    }

    fun getReviews(placeId: String): Single<List<PlaceReviewPojo>> {
        return createSingleForTask(
            taskBuilder = {
                placesReviewsCollection
                    .whereEqualTo(PLACE_ID_FIELD, placeId)
                    .get()
            },
            valueBuilder = { querySnapshot -> querySnapshot.toObjects(PlaceReviewPojo::class.java) }
        )
    }

    private companion object {
        private const val PLACES_PATH = "cafes"
        private const val REVIEWS_PATH = "cafe_reviews"
        private const val PLACE_ID_FIELD = "cafe_id"
    }
}