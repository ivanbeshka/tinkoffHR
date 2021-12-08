package com.tinkoff.hr.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.tinkoff.hr.data.Place
import com.tinkoff.hr.data.PlaceReview

class PlacesViewModel : ViewModel() {

    private val reviews = listOf(
        PlaceReview(
            "ivanbeshka2002@gmail.com",
            "300$",
            "9",
            "Пиво по 50 рублей :) Бургеры огонь",
            "Пиво уже по 89 рублей((("
        ),
        PlaceReview(
            "ivanbeshka2002@gmail.com",
            "300$",
            "9",
            "Пиво по 50 рублей :) Бургеры огонь",
            "Пиво уже по 89 рублей((("
        ),
        PlaceReview(
            "ivanbeshka2002@gmail.com",
            "300$",
            "9",
            "Пиво по 50 рублей :) Бургеры огонь",
            "Пиво уже по 89 рублей((("
        ),
        PlaceReview(
            "ivanbeshka2002@gmail.com",
            "300$",
            "9",
            "Пиво по 50 рублей :) Бургеры огонь",
            "Пиво уже по 89 рублей((("
        ),
        PlaceReview(
            "ivanbeshka2002@gmail.com",
            "300$",
            "9",
            "Пиво по 50 рублей :) Бургеры огонь",
            "Пиво уже по 89 рублей((("
        ),
        PlaceReview(
            "ivanbeshka2002@gmail.com",
            "300$",
            "9",
            "Пиво по 50 рублей :) Бургеры огонь",
            "Пиво уже по 89 рублей((("
        ),
        PlaceReview(
            "ivanbeshka2002@gmail.com",
            "300$",
            "9",
            "Пиво по 50 рублей :) Бургеры огонь",
            "Пиво уже по 89 рублей((("
        ),
        PlaceReview(
            "ivanbeshka2002@gmail.com",
            "300$",
            "9",
            "Пиво по 50 рублей :) Бургеры огонь",
            "Пиво уже по 89 рублей((("
        ),
        PlaceReview(
            "ivanbeshka2002@gmail.com",
            "300$",
            "9",
            "Пиво по 50 рублей :) Бургеры огонь",
            "Пиво уже по 89 рублей((("
        ),
        PlaceReview(
            "ivanbeshka2002@gmail.com",
            "300$",
            "9",
            "Пиво по 50 рублей :) Бургеры огонь",
            "Пиво уже по 89 рублей((("
        ),
        PlaceReview(
            "ivanbeshka2002@gmail.com",
            "300$",
            "9",
            "Пиво по 50 рублей :) Бургеры огонь",
            "Пиво уже по 89 рублей((("
        ),
        PlaceReview(
            "ivanbeshka2002@gmail.com",
            "300$",
            "9",
            "Пиво по 50 рублей :) Бургеры огонь",
            "Пиво уже по 89 рублей((("
        ),
        PlaceReview(
            "ivanbeshka2002@gmail.com",
            "300$",
            "9",
            "Пиво по 50 рублей :) Бургеры огонь",
            "Пиво уже по 89 рублей((("
        ),
        PlaceReview(
            "ivanbeshka2002@gmail.com",
            "300$",
            "9",
            "Пиво по 50 рублей :) Бургеры огонь",
            "Пиво уже по 89 рублей((("
        ),
        PlaceReview(
            "ivanbeshka2002@gmail.com",
            "300$",
            "9",
            "Пиво по 50 рублей :) Бургеры огонь",
            "Пиво уже по 89 рублей((("
        ),
        PlaceReview(
            "ivanbeshka2002@gmail.com",
            "300$",
            "9",
            "Пиво по 50 рублей :) Бургеры огонь",
            "Пиво уже по 89 рублей((("
        ),
        PlaceReview(
            "ivanbeshka2002@gmail.com",
            "300$",
            "9",
            "Пиво по 50 рублей :) Бургеры огонь",
            "Пиво уже по 89 рублей((("
        ),
        PlaceReview(
            "ivanbeshka2002@gmail.com",
            "300$",
            "9",
            "Пиво по 50 рублей :) Бургеры огонь",
            "Пиво уже по 89 рублей((("
        ),
        PlaceReview(
            "ivanbeshka2002@gmail.com",
            "300$",
            "9",
            "Пиво по 50 рублей :) Бургеры огонь",
            "Пиво уже по 89 рублей((("
        ),
        PlaceReview(
            "ivanbeshka2002@gmail.com",
            "300$",
            "9",
            "Пиво по 50 рублей :) Бургеры огонь",
            "Пиво уже по 89 рублей((("
        ),
    )

    private val places: MutableLiveData<List<Place>> by lazy {
        MutableLiveData<List<Place>>().also {
            it.value = listOf(
                Place(
                    "А ты где",
                    "да",
                    "200р",
                    "10",
                    LatLng(56.833826, 60.595112),
                    reviews
                ),
                Place(
                    "Барашка на гранате",
                    "да",
                    "200р",
                    "9",
                    LatLng(56.840183, 60.593243),
                    reviews
                ),
                Place(
                    "Бухара",
                    "нет",
                    "250-300р",
                    "4",
                    LatLng(56.836573, 60.594536),
                    reviews
                ),
                Place(
                    "Рататуй",
                    "да",
                    "350р",
                    "7,5",
                    LatLng(56.833512, 60.593203),
                    reviews
                ),
                Place(
                    "Friends",
                    "да",
                    "300р",
                    "8",
                    LatLng(56.827993, 60.598362),
                    reviews
                ),
            )
        }
    }

    fun getPlaces(): LiveData<List<Place>> = places
}