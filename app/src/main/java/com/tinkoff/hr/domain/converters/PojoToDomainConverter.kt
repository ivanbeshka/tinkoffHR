package com.tinkoff.hr.domain.converters

import com.google.android.gms.maps.model.LatLng
import com.tinkoff.hr.data.entities.*
import com.tinkoff.hr.domain.*
import com.tinkoff.hr.utils.format

fun List<EmployeePojo>.toDomainEmployees(): List<Employee> {
    return map {
        val stringTableNum = it.tableNum?.toString() ?: ""

        Employee(
            it.id,
            it.email,
            it.fio,
            it.photoUrl,
            it.bio,
            it.project,
            it.companyPosition,
            it.birthDate?.toDate()?.format(),
            it.employmentDate?.toDate()?.format(),
            stringTableNum,
            it.status,
            it.achievement
        )
    }
}

fun List<FAQPojo>.toDomainFaqs(): List<FAQ> {
    return map {
        FAQ(
            it.id,
            it.title,
            it.content
        )
    }
}

fun List<FilterPojo>.toDomainFilters(): List<Filter> {
    val filters = map {
        Filter(
            it.id,
            it.name,
            false
        )
    }

    val withAllFilter = filters.toMutableList()
    withAllFilter.add(0, Filter.allFilter())

    return withAllFilter
}

fun List<ProductPojo>.toDomainProducts(): List<Product> {
    return map {
        Product(
            it.id,
            it.article,
            it.name,
            it.count,
            it.filterIds,
            //todo
            false,
            it.photoUrl
        )
    }
}

fun PlacePojo.toDomainPlace(reviews: List<PlaceReviewPojo>): Place {
    val stringAverageCheck = averageCheck?.toString() ?: ""

    val allRating = reviews.mapNotNull { it.rating }
    val averageRating = allRating.sumOf { it.toDouble() } / allRating.size
    val stringAverageRating = if (averageRating.isNaN()) "-" else averageRating.toString()

    val latLng = LatLng(geolocation?.latitude ?: Double.NaN, geolocation?.longitude ?: Double.NaN)

    return Place(
        id,
        name,
        businessLunch,
        stringAverageCheck,
        stringAverageRating,
        latLng,
        address,
        reviews.toDomainReviews()
    )
}

private fun List<PlaceReviewPojo>.toDomainReviews(): List<PlaceReview> {
    return map {
        val stringPrice = it.price?.toString() ?: ""
        val stringRating = it.rating?.toString() ?: ""

        PlaceReview(
            it.userId,
            stringPrice,
            stringRating,
            it.pluses,
            it.minuses
        )
    }
}