package com.tinkoff.hr.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.tinkoff.hr.api.PlacesApi
import com.tinkoff.hr.data.Place
import com.tinkoff.hr.repository.PlacesRepository

class PlacesViewModel : RxViewModel() {

    private val repository = PlacesRepository(PlacesApi())

    private val places = MutableLiveData<List<Place>>().apply {
        setData(
            repository.getPlaces(),
            compositeDisposable
        )
    }

    private val place = MutableLiveData<Place?>()

    fun getPlaces(): LiveData<List<Place>> = places

    fun getPlaceById(placeId: Int): LiveData<Place?> {
        place.setData(
            repository.getPlaceById(placeId),
            compositeDisposable
        )
        return place
    }
}