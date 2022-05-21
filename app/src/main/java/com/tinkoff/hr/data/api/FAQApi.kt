package com.tinkoff.hr.data.api

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tinkoff.hr.data.entities.FAQPojo
import io.reactivex.Single

class FAQApi {
    private val faqsCollection = Firebase.firestore.collection(FAQS_PATH)

    fun getFAQs(): Single<List<FAQPojo>> {
        return Single.create { emitter ->
            faqsCollection.get()
                .addOnSuccessListener {
                    val faqs = mutableListOf<FAQPojo>()
                    it.forEach { document ->
                        val faq = document.toObject(FAQPojo::class.java)
                        faqs.add(faq)
                    }
                    emitter.onSuccess(faqs)
                }
                .addOnFailureListener {
                    emitter.onError(it)
                }
        }
    }

    private companion object {
        private const val FAQS_PATH = "faqs"
    }
}