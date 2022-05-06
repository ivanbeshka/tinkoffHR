package com.tinkoff.hr.repository

import com.tinkoff.hr.api.PlacesApi
import com.tinkoff.hr.data.Place
import io.reactivex.Maybe

class PlacesRepository(private val api: PlacesApi) {

    fun getPlaces() = api.getPlaces()
    fun getPlaceById(id: Int): Maybe<Place> =
        api.getPlaces()
            .flatMapMaybe { places ->
                val place = places.firstOrNull { it.id == id }
                return@flatMapMaybe if (place == null) {
                    Maybe.empty()
                } else {
                    Maybe.just(place)
                }
            }
}