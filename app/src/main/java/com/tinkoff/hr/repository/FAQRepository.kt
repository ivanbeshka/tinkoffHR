package com.tinkoff.hr.repository

import com.tinkoff.hr.data.api.FAQApi
import com.tinkoff.hr.domain.FAQ
import com.tinkoff.hr.domain.converters.toDomainFaqs
import io.reactivex.Single

class FAQRepository(private val api: FAQApi) {

    fun getFAQs(): Single<List<FAQ>> = api.getFAQs()
        .map {
            it.toDomainFaqs()
        }
}