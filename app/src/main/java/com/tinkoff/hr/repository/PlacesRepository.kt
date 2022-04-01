package com.tinkoff.hr.repository

import com.tinkoff.hr.api.PlacesApi
import com.tinkoff.hr.data.Place
import io.reactivex.Single

class PlacesRepository(private val api: PlacesApi) {

    fun getPlaces() = api.getPlaces()
    fun getPlaceById(id: Int): Single<Place?> =
        api.getPlaces()
            .map { places ->
                places.firstOrNull { it.id == id }
            }
}