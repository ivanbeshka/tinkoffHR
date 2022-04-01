package com.tinkoff.hr.api

import com.google.android.gms.maps.model.LatLng
import com.tinkoff.hr.data.Place
import com.tinkoff.hr.data.PlaceReview
import io.reactivex.Single

class PlacesApi {

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

    private val places = listOf(
        Place(
            1,
            "А ты где",
            "да",
            "200р",
            "10",
            LatLng(56.833826, 60.595112),
            "ул. Малышева, 29, Екатеринбург",
            reviews
        ),
        Place(
            2,
            "Барашка на гранате",
            "да",
            "200р",
            "9",
            LatLng(56.840183, 60.593243),
            "ул. Антона Валека, 12, Екатеринбург",
            reviews
        ),
        Place(
            3,
            "Бухара",
            "нет",
            "250-300р",
            "4",
            LatLng(56.836573, 60.594536),
            "просп. Ленина, 32г, Екатеринбург",
            reviews
        ),
        Place(
            4,
            "Рататуй",
            "да",
            "350р",
            "7,5",
            LatLng(56.833512, 60.593203),
            "ул. Малышева, 25, Екатеринбург",
            reviews
        ),
        Place(
            5,
            "Friends",
            "да",
            "300р",
            "8",
            LatLng(56.827993, 60.598362),
            "где-то в Гринвиче",
            reviews
        ),
    )

    fun getPlaces() = Single.just(places)
}