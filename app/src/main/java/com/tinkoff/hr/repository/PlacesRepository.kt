package com.tinkoff.hr.repository

import com.tinkoff.hr.data.api.PlacesApi
import com.tinkoff.hr.data.entities.PlacePojo
import com.tinkoff.hr.data.entities.PlaceReviewPojo
import com.tinkoff.hr.domain.Place
import com.tinkoff.hr.domain.PlaceReview
import com.tinkoff.hr.domain.converters.toDomainPlace
import com.tinkoff.hr.domain.converters.toPojoReview
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

class PlacesRepository(private val api: PlacesApi) {

    fun getPlaces(): Single<List<Place>> =
        api.getPlaces()
            .flattenAsObservable { it }
            .flatMap { place ->
                concatWithReviews(place).toObservable()
            }
            .map { (place, reviews) ->
                place.toDomainPlace(reviews)
            }
            .toList()

    fun getPlaceById(id: String): Maybe<Place> =
        api.getPlaceById(id)
            .flatMap {
                concatWithReviews(it).toMaybe()
            }
            .map { (place, reviews) ->
                place.toDomainPlace(reviews)
            }

    fun addReview(review: PlaceReview, placeId: String): Completable =
        api.addReview(
            review.toPojoReview(placeId)
        )

    private fun concatWithReviews(place: PlacePojo): Single<Pair<PlacePojo, List<PlaceReviewPojo>>> =
        api.getReviews(place.id)
            .map {
                Pair(place, it)
            }
}