package com.tinkoff.hr.viewmodels

import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

fun <T> MutableLiveData<T>.setData(
    data: Single<T>,
    compositeDisposable: CompositeDisposable
) {
    data
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(Consumer { value = it })
        .addTo(compositeDisposable)
}