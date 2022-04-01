package com.tinkoff.hr.repository

import com.tinkoff.hr.api.FAQApi

class FAQRepository(private val api: FAQApi) {

    fun getFAQs() = api.getFAQs()
}