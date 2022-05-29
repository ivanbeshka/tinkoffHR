package com.tinkoff.hr.data.api

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tinkoff.hr.data.api.common.createSingleForTask
import com.tinkoff.hr.data.entities.FAQPojo
import io.reactivex.Single

class FAQApi {
    private val faqsCollection = Firebase.firestore.collection(FAQS_PATH)

    fun getFAQs(): Single<List<FAQPojo>> {
        return createSingleForTask(
            taskBuilder = { faqsCollection.get() },
            valueBuilder = { querySnapshot -> querySnapshot.toObjects(FAQPojo::class.java) }
        )
    }

    private companion object {
        private const val FAQS_PATH = "faqs"
    }
}