package com.tinkoff.hr.repository

import com.tinkoff.hr.data.api.PlacesApi
import com.tinkoff.hr.data.entities.PlacePojo
import com.tinkoff.hr.data.entities.PlaceReviewPojo
import com.tinkoff.hr.domain.Place
import com.tinkoff.hr.domain.converters.toDomainPlace
import com.tinkoff.hr.repository.common.maybeFromNullable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

class PlacesRepository(private val api: PlacesApi) {

    fun getPlaces(): Single<List<Place>> = api.getPlaces()
        .flattenAsObservable { it }
        .flatMap { place ->
            concatWithReviews(place)
        }
        .map { (place, reviews) ->
            place.toDomainPlace(reviews)
        }
        .toList()

    fun getPlaceById(id: String): Maybe<Place> =
        api.getPlaces()
            .flatMapMaybe { places ->
                val place = places.firstOrNull { it.id == id }
                    ?.toDomainPlace(emptyList())

                maybeFromNullable(place)
            }

    private fun concatWithReviews(place: PlacePojo): Observable<Pair<PlacePojo, List<PlaceReviewPojo>>> =
        api.getReviews(place.id)
            .map {
                Pair(place, it)
            }
            .toObservable()
}