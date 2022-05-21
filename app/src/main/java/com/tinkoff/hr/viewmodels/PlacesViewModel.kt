package com.tinkoff.hr.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tinkoff.hr.data.api.PlacesApi
import com.tinkoff.hr.domain.Place
import com.tinkoff.hr.repository.PlacesRepository
import com.tinkoff.hr.viewmodels.common.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class PlacesViewModel : RxViewModel() {

    private val repository = PlacesRepository(PlacesApi())

    private val place = MutableLiveData<ScreenState<Place>>()
    private val places = MutableLiveData<ScreenState<List<Place>>>()

    fun getPlaces(): LiveData<ScreenState<List<Place>>> {
        repository.getPlaces()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { places.value = LoadingScreenState() }
            .subscribeBy(
                onSuccess = {
                    places.value = SuccessScreenState(it)
                },
                onError = {
                    places.value = ErrorScreenState(it)
                }
            )
            .disposeOnFinish()

        return places
    }

    fun getPlaceById(placeId: String): LiveData<ScreenState<Place>> {
        repository.getPlaceById(placeId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                place.value = LoadingScreenState()
            }
            .subscribeBy(
                onSuccess = {
                    place.value = SuccessScreenState(it)
                },
                onComplete = {
                    place.value = ErrorScreenState(NoSuchElementException())
                }
            )
            .disposeOnFinish()

        return place
    }
}