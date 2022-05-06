package com.tinkoff.hr.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tinkoff.hr.api.FAQApi
import com.tinkoff.hr.data.FAQ
import com.tinkoff.hr.repository.FAQRepository
import com.tinkoff.hr.viewmodels.common.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class FAQViewModel : RxViewModel() {

    private val repository = FAQRepository(FAQApi())

    private val currentFAQ = MutableLiveData<FAQ>()
    private val faqs = MutableLiveData<ScreenState<List<FAQ>>>()

    fun getFAQs(): LiveData<ScreenState<List<FAQ>>> {
        repository.getFAQs()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { faqs.value = LoadingScreenState() }
            .subscribeBy(
                onSuccess = {
                    faqs.value = SuccessScreenState(it)
                },
                onError = {
                    faqs.value = ErrorScreenState(it)
                }
            )
            .disposeOnFinish()

        return faqs
    }

    fun setCurrentFAQ(faq: FAQ) {
        currentFAQ.value = faq
    }

    fun getCurrentFAQ(): LiveData<FAQ> = currentFAQ
}