package com.tinkoff.hr.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinkoff.hr.api.FAQApi
import com.tinkoff.hr.data.FAQ
import com.tinkoff.hr.repository.FAQRepository

class FAQViewModel : RxViewModel() {

    private val repository = FAQRepository(FAQApi())

    private val currentFAQ = MutableLiveData<FAQ>()
    private val faqs = MutableLiveData<List<FAQ>>().apply {
        setData(
            repository.getFAQs(),
            compositeDisposable
        )
    }

    fun getFAQs(): LiveData<List<FAQ>> = faqs
    fun setCurrentFAQ(faq: FAQ){
        currentFAQ.value = faq
    }
    fun getCurrentFAQ(): LiveData<FAQ> = currentFAQ
}