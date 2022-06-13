package com.tinkoff.hr.domain.converters

import com.google.android.gms.maps.model.LatLng
import com.tinkoff.hr.data.entities.*
import com.tinkoff.hr.domain.*
import com.tinkoff.hr.utils.format

fun List<EmployeePojo>.toDomainEmployees(): List<Employee> {
    return map {
        val stringTableNum = it.tableNum?.toString() ?: ""

        Employee(
            id = it.id,
            email = it.email,
            fio = it.fio,
            photoUrl = it.photoUrl,
            bio = it.bio,
            project = it.project,
            companyPosition = it.companyPosition,
            birthDate = it.birthDate?.toDate()?.format(),
            employmentDate = it.employmentDate?.toDate()?.format(),
            tableNum = stringTableNum,
            status = it.status,
            achievement = it.achievement
        )
    }
}

fun List<FAQPojo>.toDomainFaqs(): List<FAQ> {
    return map {
        FAQ(
            id = it.id,
            title = it.title,
            content = it.content
        )
    }
}

fun List<FilterPojo>.toDomainFilters(): List<Filter> {
    val filters = map {
        Filter(
            id = it.id,
            name = it.name,
            isSelected = false
        )
    }

    val withAllFilter = filters.toMutableList()
    withAllFilter.add(0, Filter.allFilter())

    return withAllFilter
}

fun List<ProductPojo>.toDomainProducts(): List<Product> {
    return map {
        Product(
            id = it.id,
            article = it.article,
            name = it.name,
            count = it.count,
            categories = it.filterIds,
            //todo
            selected = false,
            photoUrl = it.photoUrl
        )
    }
}

fun PlacePojo.toDomainPlace(reviews: List<PlaceReviewPojo>): Place {
    val averageCheck = reviews.mapNotNull { it.price }.toTypedArray().average()
    val stringAverageCheck = if (averageCheck.isNaN()) "-" else averageCheck.toString()

    val averageRating = reviews.mapNotNull { it.rating }.toTypedArray().average()
    val stringAverageRating = if (averageRating.isNaN()) "-" else averageRating.toString()

    val latLng = LatLng(geolocation?.latitude ?: Double.NaN, geolocation?.longitude ?: Double.NaN)

    return Place(
        id = id,
        name = name,
        lunch = businessLunch,
        averageCheck = stringAverageCheck,
        rating = stringAverageRating,
        latLng = latLng,
        address = address,
        reviews = reviews.toDomainReviews()
    )
}

private fun List<PlaceReviewPojo>.toDomainReviews(): List<PlaceReview> {
    return map {
        PlaceReview(
            userId = it.userId,
            price = it.price,
            rating = it.rating,
            pluses = it.pluses,
            minuses = it.minuses
        )
    }
}