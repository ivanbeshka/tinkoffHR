package com.tinkoff.hr.data.api

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tinkoff.hr.data.api.common.createCompletableForTask
import com.tinkoff.hr.data.api.common.createSingleForQuery
import com.tinkoff.hr.data.entities.PlacePojo
import com.tinkoff.hr.data.entities.PlaceReviewPojo
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

class PlacesApi {

    private val placesCollection = Firebase.firestore.collection(PLACES_PATH)
    private val placesReviewsCollection = Firebase.firestore.collection(REVIEWS_PATH)

    fun getPlaces(): Single<List<PlacePojo>> {
        return createSingleForQuery(
            taskBuilder = { placesCollection.get() },
            valueBuilder = { querySnapshot -> querySnapshot.toObjects(PlacePojo::class.java) }
        )
    }

    fun getPlaceById(id: String): Maybe<PlacePojo> {
        return Maybe.create { emitter ->
            placesCollection.document(id).get()
                .addOnSuccessListener { documentSnapshot ->
                    val place = documentSnapshot.toObject(PlacePojo::class.java)
                    place?.let { emitter.onSuccess(it) }
                }
                .addOnFailureListener {
                    emitter.onError(it)
                }
        }
    }

    fun getReviews(placeId: String): Single<List<PlaceReviewPojo>> {
        return createSingleForQuery(
            taskBuilder = {
                placesReviewsCollection
                    .whereEqualTo(PLACE_ID_FIELD, placeId)
                    .get()
            },
            valueBuilder = { querySnapshot -> querySnapshot.toObjects(PlaceReviewPojo::class.java) }
        )
    }

    fun addReview(review: PlaceReviewPojo): Completable {
        return createCompletableForTask {
            placesReviewsCollection.add(review)
        }
    }

    private companion object {
        private const val PLACES_PATH = "cafes"
        private const val REVIEWS_PATH = "cafe_reviews"
        private const val PLACE_ID_FIELD = "cafe_id"
    }
}